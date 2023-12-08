package model.bo;
import java.util.Optional;
import model.bean.Category;
import model.dao.CategoryDAO;
public class CategoryBO {
	
	CategoryDAO categoryDAO = new CategoryDAO();
	
	
	public Category[] getListCategories() {
		// TODO Auto-generated method stub
		return categoryDAO.getListCategory();
	}
}
