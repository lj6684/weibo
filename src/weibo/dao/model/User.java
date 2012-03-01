package weibo.dao.model;

public class User {
	
	private int uid;
	private String name;
	private String nickName;
	private String local;
	private String headImg;
	private String password;
	
	private int fansCount;
	private int followCount;
	private int messageCount;
	
	
	public int getFansCount() {
		return fansCount;
	}
	public void setFansCount(int fansCount) {
		this.fansCount = fansCount;
	}
	public int getFollowCount() {
		return followCount;
	}
	public void setFollowCount(int followCount) {
		this.followCount = followCount;
	}
	
	@Override
	public String toString() {
		return "User [uid=" + uid + ", name=" + name + ", nickName=" + nickName
				+ ", local=" + local + ", headImg=" + headImg + ", password="
				+ password + ", fansCount=" + fansCount + ", followCount="
				+ followCount + ", messageCount=" + messageCount + "]";
	}
	public int getMessageCount() {
		return messageCount;
	}
	public void setMessageCount(int messageCount) {
		this.messageCount = messageCount;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
