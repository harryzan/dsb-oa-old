package gov.dsb.core.dao;

import gov.dsb.core.domain.DocCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: harryzan
 * Date: 9/16/12
 * Time: 10:57 AM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = {"classpath:applicationContext.xml", "classpath:applicationContext-database.xml"})
public class DocCategoryDaoTest {

    @Autowired
    public DocCategoryDao dao;

    @Test
    public void test() {
        List<DocCategory> docCategories = dao.findAll();
        System.out.println("**************** docCategories.size() = " + docCategories.size());
//        for (DocCategory docCategory : docCategories) {
//            System.out.println("docCategory = " + docCategory);
//        }
    }
}
