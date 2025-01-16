package jblog.vo;

public class PostVo {
	
	private int id;
	private String title;
	private String contents;
	private String regDate;
	private int categoryId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getRegDate() {
		return regDate;
	}

	@Override
	public String toString() {
		return "PostVo [id=" + id + ", title=" + title + ", contents=" + contents + ", regDate=" + regDate
				+ ", categoryId=" + categoryId + "]";
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

}