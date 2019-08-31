package cn.skill6.website.dao.impl.article;

import cn.skill6.common.entity.enums.SortType;
import cn.skill6.common.entity.po.article.ArticleInfo;
import cn.skill6.common.entity.to.ArticleInfoTo;
import cn.skill6.website.Skill6WebsiteApplicationBase;
import cn.skill6.website.dao.intf.article.ArticleInfoDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * 文章信息实现测试
 *
 * @author 何明胜
 * @version 1.2
 * @since 2018年9月21日 下午11:24:25
 */
@Transactional
public class ArticleInfoDaoOperTest extends Skill6WebsiteApplicationBase {

    @Autowired
    private ArticleInfoDao articleInfoDao;

    @Autowired
    private ArticleInfoTo articleInfoTo;

    @Test
    public void test01AddArticleInfo() {
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setArticleTitle("001");
        articleInfo.setArticleAuthor("hemingsheng");
        articleInfo.setArticleSummary("asg");
        articleInfo.setArticleLabel("sdg");
        articleInfo.setCategoryId(123L);
        articleInfo.setArticleHtmlContent("gf");
        articleInfo.setArticleMdContent("hkjhkj");
        articleInfoDao.addArticleInfo(articleInfo);
    }

    @Test
    public void testFindByParams() {
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setArticleAuthor("何明胜");

        // 根据哪个字段排序
        articleInfo.setOrderBy(articleInfoTo.getArticleAuthor());

        // 排序方式
        articleInfo.setSortType(SortType.DESCENDING);

        // 设置分页
        articleInfo.setPageNum(1);
        articleInfo.setPageSize(10);

        List<ArticleInfo> articleInfos = articleInfoDao.findByParams(articleInfo);

        for (ArticleInfo articleInfo2 : articleInfos) {
            System.out.println(articleInfo2.getArticleId());
        }

        assertTrue(true);
    }

    @Test
    public void test02ModifyByArticleId() {
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setArticleTitle("001");
        articleInfo.setArticleAuthor("sfher");
        articleInfo.setArticleSummary("nihk");
        articleInfo.setArticleLabel("sldjfjk");
        articleInfo.setArticleId(123L);
        articleInfo.setArticleHtmlContent("halsdfhj");
        articleInfo.setArticleMdContent("hkjhkj");
        articleInfo.setCategoryId(123L);

        Long id = articleInfoDao.addArticleInfo(articleInfo);
        articleInfo.setArticleId(id);
        articleInfoDao.modifyByArticleId(articleInfo);
    }

    @Test
    public void test01JudgeExist() {
        boolean judgeResult = articleInfoTo.judgeFieldIsExist("articleAuthor");
        assertTrue(judgeResult);
    }

    @Test
    public void test02JudgeExist() {
        boolean judgeResult = articleInfoTo.judgeFieldIsExist("articleAuthor1");
        assertFalse(judgeResult);
    }
}
