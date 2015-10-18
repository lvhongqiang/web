/**
 * 
 */
package baking.model.vo;

import java.util.List;

import baking.model.Step;

/**
 * @author Lv
 * @date 2015年10月16日
 * @version 1.0
 */
public class PrepareStep {

	private Step step;
	private List<Prepare>list;
	
	public PrepareStep() {
	}
	public PrepareStep(Step step, List<Prepare> list) {
		this.step = step;
		this.list = list;
	}
	public Step getStep() {
		return step;
	}
	public void setStep(Step step) {
		this.step = step;
	}
	public List<Prepare> getList() {
		return list;
	}
	public void setList(List<Prepare> list) {
		this.list = list;
	}
}
