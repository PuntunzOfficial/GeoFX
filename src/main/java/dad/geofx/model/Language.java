package dad.geofx.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("json")
public class Language {

	@SerializedName("code")
	@Expose
	private String code;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("native")
	@Expose
	private String _native;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Language withCode(String code) {
		this.code = code;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Language withName(String name) {
		this.name = name;
		return this;
	}

	public String getNative() {
		return _native;
	}

	public void setNative(String _native) {
		this._native = _native;
	}

	public Language withNative(String _native) {
		this._native = _native;
		return this;
	}

}