package xx.action;

import org.springframework.beans.factory.annotation.Autowired;

import xx.service.InputService;

public class InputAction extends BaseAction {
	@Autowired private InputService inputService;
	
	private String url;	
	private Integer aid;
	
	@Override
	public String execute(){
		aid=inputService.saveWeixin(url);
		return SUCCESS;
	}

	public Integer getAid() {
		return aid;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
