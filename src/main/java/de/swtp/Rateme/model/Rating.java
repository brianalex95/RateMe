package de.swtp.Rateme.model;

import java.sql.Date;

public class Rating {
	private int ratingid;
	private int userid;
	private long osmid;
	private String ratingtype;
	private int grade;
	private String txt;
	private String imagepath;
	private String createdt;
	private Date modifydt;

	public Rating() {
		super();
	}

	public Rating(int ratingid, int userid, long osmid, String ratingtype, int grade, String txt, String imagepath,
			String createdt, Date modifydt) {
		super();
		this.ratingid = ratingid;
		this.userid = userid;
		this.osmid = osmid;
		this.ratingtype = ratingtype;
		this.grade = grade;
		this.txt = txt;
		this.imagepath = imagepath;
		this.createdt = createdt;
		this.modifydt = modifydt;
	}

	public Rating(int userid, long osmid, String ratingtype, int grade, String txt, String imagepath) {
		super();
		this.userid = userid;
		this.osmid = osmid;
		this.ratingtype = ratingtype;
		this.grade = grade;
		this.txt = txt;
		this.imagepath = imagepath;
	}

	public int getRatingid() {
		return ratingid;
	}

	public int getUserid() {
		return userid;
	}

	public long getOsmid() {
		return osmid;
	}

	public String getRatingtype() {
		return ratingtype;
	}

	public int getGrade() {
		return grade;
	}

	public String getTxt() {
		return txt;
	}

	public String getImagepath() {
		return imagepath;
	}

	public String getCreatedt() {
		return createdt;
	}

	public Date getModifydt() {
		return modifydt;
	}

	public void setRatingid(int ratingid) {
		this.ratingid = ratingid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public void setOsmid(long osmid) {
		this.osmid = osmid;
	}

	public void setRatingtype(String ratingtype) {
		this.ratingtype = ratingtype;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}

	public void setCreatedt(String createdt) {
		this.createdt = createdt;
	}

	public void setModifydt(Date modifydt) {
		this.modifydt = modifydt;
	}

	@Override
	public String toString() {
		return "Rating [ratingid=" + ratingid + ", userid=" + userid + ", osmid=" + osmid + ", ratingtype=" + ratingtype
				+ ", grade=" + grade + ", txt=" + txt + ", imagepath=" + imagepath + ", createdt=" + createdt
				+ ", modifydt=" + modifydt + "]";
	}

}
