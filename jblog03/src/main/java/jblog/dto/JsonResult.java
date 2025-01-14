package jblog.dto;

public class JsonResult {
	private String result;
	private Object data;
	private String message;
	
	public static JsonResult success(Object data){
		return new JsonResult(data);
	}
	
	private JsonResult(Object data) {
		this.result = "success";
		this.data = data;
		this.message = null;
	}
	
	public static JsonResult fail(String message){
		return new JsonResult(message);
	}
	
	private JsonResult(String message) {
		this.result = "fail";
		this.data = null;
		this.message = message;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
