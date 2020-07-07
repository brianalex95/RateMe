package de.swtp.Rateme.model;

public class PoiTag {
	private Long osmId;
	private String tag;
	private String value;

	public PoiTag() {
		super();
	}

	public PoiTag(Long osmId, String tag, String value) {
		this.osmId = osmId;
		this.tag = tag;
		this.value = value;
	}

	public Long getOsmId() {
		return osmId;
	}

	public String getTag() {
		return tag;
	}

	public String getValue() {
		return value;
	}

	public void setOsmId(Long osmId) {
		this.osmId = osmId;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "PoiTag [osmId=" + osmId + ", tag=" + tag + ", value=" + value + "]";
	}

}
