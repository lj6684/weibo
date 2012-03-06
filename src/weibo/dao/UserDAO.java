package weibo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.dbcp.dbcp.ConnectionFactory;

import weibo.dao.model.User;

public class UserDAO {
	
	/**
	 * 保存用户
	 * @param user
	 * @return
	 */
	public int saveUser(User user) {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "insert into user(name, nick_name, password, local, head_img) values (?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getNickName());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getLocal());
			pstmt.setString(5, user.getHeadImg());
			
			int count = pstmt.executeUpdate();
			return count;
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
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
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		try {
			String sql = "select * from user where uid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			rst = pstmt.executeQuery();
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
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, user.getUid());
				rst = pstmt.executeQuery();
				if(rst.next()) {
					int followCount = rst.getInt(1);
					user.setFollowCount(followCount);
				}
				
				// 广播数
				sql = "select count(mid) from message where uid=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, user.getUid());
				rst = pstmt.executeQuery();
				if(rst.next()) {
					int messageCount = rst.getInt(1);
					user.setMessageCount(messageCount);
				}
				
				// 粉丝数
				sql = "select count(uid) from message where tid=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, user.getUid());
				rst = pstmt.executeQuery();
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
				if(pstmt != null) {
					pstmt.close();
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
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		try {
			String sql = "select * from user where name=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rst = pstmt.executeQuery();
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
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, user.getUid());
				rst = pstmt.executeQuery();
				if(rst.next()) {
					int followCount = rst.getInt(1);
					user.setFollowCount(followCount);
				}
				
				// 广播数
				sql = "select count(mid) from message where uid=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, user.getUid());
				rst = pstmt.executeQuery();
				if(rst.next()) {
					int messageCount = rst.getInt(1);
					user.setMessageCount(messageCount);
				}
				
				// 粉丝数
				sql = "select count(uid) from contact where tid=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, user.getUid());
				rst = pstmt.executeQuery();
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
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				
			}
		}
	}
	
	/**
	 * 按昵称查询用户列表(昵称中包含关键字，并且此用户未添加好友)
	 * @param nickName
	 * @return
	 */
	public List<User> findUserByNickName(int uid, String nickName) {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		List<User> userList = new ArrayList<User>();
		try {
			String sql = "select * from user where nick_name like ? and uid not in (select tid from contact where uid=?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + nickName + "%");
			pstmt.setInt(2, uid);
			rst = pstmt.executeQuery();
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
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				
			}
		}
		return userList;
	}
	
	public List<User> getTagetUsers(int uid) {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		List<User> userList = new ArrayList<User>();
		try {
			String sql = "select * from user where uid in (select tid from contact where uid=?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			rst = pstmt.executeQuery();
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
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				
			}
		}
		return userList;
	}
	
	public List<User> getFansUsers(int uid) {
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		List<User> userList = new ArrayList<User>();
		try {
			String sql = "select * from user where uid in (select uid from contact where tid=?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			rst = pstmt.executeQuery();
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
				if(pstmt != null) {
					pstmt.close();
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
