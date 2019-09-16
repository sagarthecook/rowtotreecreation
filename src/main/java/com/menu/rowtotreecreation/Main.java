package sorttree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		List<Menu> menus = new ArrayList<Menu>();
		menus.add(new Menu("A", "1.", true));
		menus.add(new Menu("AB", "1.1", false));
		menus.add(new Menu("B", "2.", true));
		menus.add(new Menu("C", "3.", true));
		menus.add(new Menu("BA", "2.1", false));
		menus.add(new Menu("BC", "2.2", false));

		List<Menu> headerList = new ArrayList<Menu>();
		List<Menu> childrenList = new ArrayList<Menu>();
		menus.forEach(menu -> {
			if (menu.getIsHeader()) {
				headerList.add(menu);
			} else {
				childrenList.add(menu);
			}
		});

		List<Menu> finalTreeList=new ArrayList<Menu>();
		for (Iterator<Menu> iterator = headerList.iterator(); iterator.hasNext();) {
			Menu menu2 = (Menu) iterator.next();
			finalTreeList.add(Main.createSubmenuInAscOrder(childrenList,menu2));
		}

		System.out.println(finalTreeList.toString());
	}

	// TO CREATE SUBMNEU WITH ASSENDING ORDER
	private static Menu  createSubmenuInAscOrder(List<Menu> submenuList, Menu parentMenu) {
		Menu headerMenuWithSubmenu = new Menu(parentMenu.getName(), parentMenu.getTreeId(), parentMenu.getIsHeader());
		List<Menu> sortedMenu = submenuList.stream().parallel().sorted(Comparator.comparingInt(Menu::getTreeHiddenId))
				.collect(Collectors.toList());

		// HEADE MENU TREE LENGTH COUNT
		int headerMenuLength = parentMenu.getTreeId().length();

		for (Iterator<Menu> iterator = sortedMenu.iterator(); iterator.hasNext();) {
			Menu subMenu = (Menu) iterator.next();
			String subString = subMenu.getTreeId().substring(0, headerMenuLength);
			if (subString.equalsIgnoreCase(parentMenu.getTreeId())) {
				headerMenuWithSubmenu.addFeature(subMenu);	
			}
		}
		return headerMenuWithSubmenu;
	}

}
