package weibo.dao.model;


public class Message {
	
	private int mid;
	private int uid;
	private String content;
	private String createTime;
	private String img;
	
	private String authorName;
	private String authorImg;
	
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getAuthorImg() {
		return authorImg;
	}
	public void setAuthorImg(String authorImg) {
		this.authorImg = authorImg;
	}
	@Override
	public String toString() {
		return "Message [mid=" + mid + ", uid=" + uid + ", content=" + content
				+ ", createTime=" + createTime + ", img=" + img
				+ ", authorName=" + authorName + ", authorImg=" + authorImg
				+ "]";
	}
}
