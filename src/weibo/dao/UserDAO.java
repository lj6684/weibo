package weibo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import weibo.dao.model.User;

public class UserDAO {
	
	/**
	 * 保存用户
	 * @param user
	 * @return
	 */
	public int saveUser(User user) {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "insert into user(name, nick_name, password, local, head_img) values (?, ?, ?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getNickName());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getLocal());
			stmt.setString(5, user.getHeadImg());
			
			int count = stmt.executeUpdate();
			return count;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		} finally {
			try {
				if(stmt != null) {
					stmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				
			}
		}
	}
	
	/**
	 * 按用户ID查询
	 * @param uid
	 * @return
	 */
	public User findUser(int uid) {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement stmt = null;
		ResultSet rst = null;
		try {
			String sql = "select * from user where uid=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, uid);
			rst = stmt.executeQuery();
			if(rst.next()) {
				// 用户基本信息
				User user = new User();
				user.setUid(rst.getInt("uid"));
				user.setName(rst.getString("name"));
				user.setNickName(rst.getString("nick_name"));
				user.setPassword(rst.getString("password"));
				user.setLocal(rst.getString("local"));
				user.setHeadImg(rst.getString("head_img"));
				
				// 关注数
				sql = "select count(tid) from contact where uid=?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, user.getUid());
				rst = stmt.executeQuery();
				if(rst.next()) {
					int followCount = rst.getInt(1);
					user.setFollowCount(followCount);
				}
				
				// 广播数
				sql = "select count(mid) from message where uid=?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, user.getUid());
				rst = stmt.executeQuery();
				if(rst.next()) {
					int messageCount = rst.getInt(1);
					user.setMessageCount(messageCount);
				}
				
				// 粉丝数
				sql = "select count(uid) from message where tid=?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, user.getUid());
				rst = stmt.executeQuery();
				if(rst.next()) {
					int fansCount = rst.getInt(1);
					user.setFansCount(fansCount);
				}
				
				return user;
			} else {
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			try {
				if(rst != null) {
					rst.close();
				}
				if(stmt != null) {
					stmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				
			}
		}
	}
	
	/**
	 * 按用户名查询
	 * @param nickName
	 * @return
	 */
	public User findUser(String name) {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement stmt = null;
		ResultSet rst = null;
		try {
			String sql = "select * from user where name=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			rst = stmt.executeQuery();
			if(rst.next()) {
				// 用户基本信息
				User user = new User();
				user.setUid(rst.getInt("uid"));
				user.setName(rst.getString("name"));
				user.setNickName(rst.getString("nick_name"));
				user.setPassword(rst.getString("password"));
				user.setLocal(rst.getString("local"));
				user.setHeadImg(rst.getString("head_img"));
				
				// 关注数
				sql = "select count(tid) from contact where uid=?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, user.getUid());
				rst = stmt.executeQuery();
				if(rst.next()) {
					int followCount = rst.getInt(1);
					user.setFollowCount(followCount);
				}
				
				// 广播数
				sql = "select count(mid) from message where uid=?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, user.getUid());
				rst = stmt.executeQuery();
				if(rst.next()) {
					int messageCount = rst.getInt(1);
					user.setMessageCount(messageCount);
				}
				
				// 粉丝数
				sql = "select count(uid) from contact where tid=?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, user.getUid());
				rst = stmt.executeQuery();
				if(rst.next()) {
					int fansCount = rst.getInt(1);
					user.setFansCount(fansCount);
				}
				
				return user;
			} else {
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			try {
				if(rst != null) {
					rst.close();
				}
				if(stmt != null) {
					stmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				
			}
		}
	}
	
	/**
	 * 按昵称查询用户列表
	 * @param nickName
	 * @return
	 */
	public List<User> findUserByNickName(String nickName) {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement stmt = null;
		ResultSet rst = null;
		List<User> userList = new ArrayList<User>();
		try {
			String sql = "select * from user where nick_name like ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + nickName + "%");
			rst = stmt.executeQuery();
			while(rst.next()) {
				// 用户基本信息
				User user = new User();
				user.setUid(rst.getInt("uid"));
				user.setName(rst.getString("name"));
				user.setNickName(rst.getString("nick_name"));
				user.setPassword(rst.getString("password"));
				user.setLocal(rst.getString("local"));
				user.setHeadImg(rst.getString("head_img"));
				
				userList.add(user);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if(rst != null) {
					rst.close();
				}
				if(stmt != null) {
					stmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				
			}
		}
		return userList;
	}

}
