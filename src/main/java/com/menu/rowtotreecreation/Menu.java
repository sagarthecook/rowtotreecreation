package sorttree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sagar
 * THIS DTO IS USD FOR MENU CREATION
 */
public class Menu {

	private String name, treeId;
	private Integer treeHiddenId;
	private Boolean isHeader;
	private List<Menu> features = new ArrayList<Menu>();

	public Menu() {

	}

	public Menu(String name, String treeId, Boolean isHeader) {
		super();
		this.name = name;
		this.treeId = treeId;
		this.isHeader = isHeader;
		this.setTreeHiddenId(treeHiddenId);
	}

	public List<Menu> getFeatures() {
		if (features != null && features.size() > 0) {
			this.features = this.features.stream().sorted(Comparator.comparingInt(Menu::getTreeHiddenId))
					.collect(Collectors.toList());
		}
		return features;
	}

	public Integer getTreeHiddenId() {
		return treeHiddenId;
	}

	public void setTreeHiddenId(Integer treeHiddenId) {
		this.treeHiddenId = new Integer(this.treeId.replace(".", ""));
	}

	public Boolean getIsHeader() {
		return isHeader;
	}

	public String getName() {
		return name;
	}

	public String getTreeId() {
		return treeId;
	}

	// TO ADD FEATURE
	public void addFeature(Menu menu) {
		if (features != null) {
			this.features.add(menu);
		}
	}

	@Override
	public String toString() {
		return "Menu [name=" + name + ", treeId=" + treeId + ", treeHiddenId=" + treeHiddenId + ", isHeader=" + isHeader
				+ ", features=" + features + "]";
	}

}
