/**
 * 
 */
package ia.umkc.career;

/**
 * @author rr5h4
 *
 */
public class MainMenuDTO {
	
	private String menuName;
	
	
	public MainMenuDTO(String menu) {
		this.menuName = menu;
	}
	/**
	 * @return the menuName
	 */
	public String getMenuName() {
		return menuName;
	}
	/**
	 * @param menuName the menuName to set
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

}
