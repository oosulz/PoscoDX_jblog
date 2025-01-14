package jblog.vo;

public class BlogVo {
	private String blogId;
	private String title;
	private String profile;

	public String getBlogId() {
		return blogId;
	}

	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	@Override
	public String toString() {
		return "BlogVo [id=" + blogId + ", title=" + title + ", profile=" + profile + "]";
	}

}
