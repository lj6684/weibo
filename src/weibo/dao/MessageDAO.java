package weibo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import weibo.dao.model.Message;

public class MessageDAO {
	
	/**
	 * 保存微博
	 * @param message
	 * @return
	 */
	public int saveMessage(Message message) {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "insert into message(uid, content, img, create_time) values(?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, message.getUid());
			stmt.setString(2, message.getContent());
			stmt.setString(3, message.getImg());
			stmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			
			return stmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		} finally {
			try {
				if(conn != null) {
					conn.close();
				}
				if(stmt != null) {
					stmt.close();
				}
			} catch (Exception ex) {
				
			}
		}
	}
	
	/**
	 * 查看微博列表(本人和关注人发布的微博）
	 * @return
	 */
	public List<Message> getMessages(int uid) {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		List<Message> messageList = new ArrayList<Message>();
		try {
			String sql = "SELECT m.mid, m.content, m.img, m.create_time, u.uid, u.nick_name, u.head_img " +
							"FROM message m " +
							"NATURAL JOIN user u " +
							"WHERE u.uid=? or u.uid in (SELECT tid FROM contact WHERE uid=?) " +
							"ORDER BY m.create_time DESC";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			pstmt.setInt(2, uid);
			
			rst = pstmt.executeQuery();
			while(rst.next()) {
				Message message = new Message();
				message.setMid(rst.getInt("mid"));
				message.setContent(rst.getString("content"));
				message.setImg(rst.getString("img"));
				message.setCreateTime(rst.getTimestamp("create_time").toString());
				message.setUid(rst.getInt("uid"));
				message.setAuthorName(rst.getString("nick_name"));
				message.setAuthorImg(rst.getString("head_img"));
				
				messageList.add(message);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			try {
				if(conn != null) {
					conn.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
			} catch (Exception ex) {
				
			}
		}
		return messageList;
	}
	
	public static void main(String[] args) {
		MessageDAO dao = new MessageDAO();
		/*
		Message message = new Message();
		message.setUid(1);
		message.setContent("test2");
		message.setImg("/img/upload/test.jpge");
		
		dao.saveMessage(message);
		*/
		List<Message> messageList = dao.getMessages(7);
		for(Message message : messageList) {
			System.out.println(message);
		}
	}

}
