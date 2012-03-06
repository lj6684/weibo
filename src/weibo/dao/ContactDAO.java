package weibo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ContactDAO {
	
	public int addContact(int uid, int tid) {
		int res = 0;
		Connection conn = ConnectionManager.getInstance().getConnection();
		PreparedStatement pstmt = null;
		try {
			String sql = "INSERT INTO contact(uid, tid) VALUES(?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			pstmt.setInt(2, tid);
			res = pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				// do nothing
			}
		}
		return res;
	}	

}
