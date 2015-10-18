/**
 * 
 */
package baking.model.vo;

import java.util.List;

/**
 * @author Lv
 * @date 2015年10月16日
 * @version 1.0
 */
public class PrepareGroup {

	private String title;
	private List<PrepareStep>steplist;
	public PrepareGroup() {
	}
	public PrepareGroup(String title, List<PrepareStep> steplist) {
		this.title = title;
		this.steplist = steplist;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<PrepareStep> getSteplist() {
		return steplist;
	}
	public void setSteplist(List<PrepareStep> steplist) {
		this.steplist = steplist;
	}
}
