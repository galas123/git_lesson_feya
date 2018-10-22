package ru.yandex.webtestslessons;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import ru.yandex.aqua.annotations.project.Aqua;
import ru.yandex.autotests.direct.web.DirectWebTag;
import ru.yandex.autotests.direct.web.data.factory.BeanFactories;
import ru.yandex.autotests.direct.web.data.factory.templates.TemplateEnum;
import ru.yandex.autotests.direct.web.features.TestFeatures;
import ru.yandex.autotests.direct.web.objects.banners.BannerInfoWeb;
import ru.yandex.autotests.direct.web.objects.banners.commons.SiteLinkInfoWeb;
import ru.yandex.autotests.direct.web.steps.UserSteps;
import ru.yandex.autotests.direct.web.util.WebRuleFactory;
import ru.yandex.autotests.directapi.model.User;
import ru.yandex.qatools.Tag;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;
import ru.yandex.qatools.allure.webdriver.rules.WebDriverConfiguration;

import static org.junit.Assert.assertThat;
import static ru.yandex.autotests.direct.utils.beans.BeanWrapper.wrap;
import static ru.yandex.autotests.direct.web.TestEnvironment.getWebDriverConfiguration;
import static ru.yandex.autotests.direct.web.util.helpers.campaigns.BannersHelper.getSiteLinksArrayByCount;
import static ru.yandex.autotests.irt.testutils.beandiffer2.BeanDifferMatcher.beanDiffer;
import static ru.yandex.autotests.direct.web.data.TestLogins.AT_DIRECT_VIEWSLINK_C1;


@Aqua.Test
@Title("Проверка сохранения быстрых ссылок")
@Tag(DirectWebTag.DIRECT_WEB)
@Tag(DirectWebTag.Feature.SITE_LINKS)
@Tag(DirectWebTag.Page.EDIT_GROUP)
@Tag(DirectWebTag.Cmd.SHOW_CAMP_MULTI_EDIT)
@Tag(DirectWebTag.CampaignType.TEXT_CAMP)
@Tag(DirectWebTag.AdType.TEXT_AD)
public class SaveSiteLinksTest {

    private static final User account = User.get(AT_DIRECT_VIEWSLINK_C1.getLogin());

    public static Rule defaultClassRuleChain = WebRuleFactory.defaultClassRuleChain();
    public Rule defaultRuleChain = WebRuleFactory.defaultRuleChain(config);

    public WebDriverConfiguration config = getWebDriverConfiguration();
    public UserSteps user;

    public Integer siteLinkCount;

    private Long campaignId;
    private BannerInfoWeb defaultBanner;
    private SiteLinkInfoWeb[] siteLinksArray;

    // Количество быстрых ссылок:
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {1},
                {2},
                {3},
                {4}
        });
    }

    public void before() {
        user = UserSteps.getInstance(UserSteps.class, config);
        defaultBanner = BeanFactories.bannersFactory().getBean(TemplateEnum.TextBanner.BANNER_WITH_REQUIRED_FIELDS);
        siteLinksArray = getSiteLinksArrayByCount(siteLinkCount);
        user.authorizeAs(account);
        campaignId = user.byUsingBackend().createClientActiveCampaign(account.getLogin());
        defaultBanner.setSitelinks(siteLinksArray);
        user.inBrowserAddressBar().openShowCampPage(campaignId);
        user.onShowCampPage().clickAddBannerButton();
    }

    public void after() {
        user.byUsingBackend().deleteCampaigns(campaignId);
    }

    // Проверка сохранения объявления с заданным количеством быстрых ссылок
    public void canSaveBannerWithOneSiteLinkTest() {
        user.onAddNewBannerPage().fillBannerParameters(wrap(defaultBanner));
        user.onAddNewBannerPage().goNextAndCheckNextPageURL();
        SiteLinkInfoWeb[] actualSiteLinks =
                user.byUsingBackend().getBannerInfo(user.byUsingBackend().getBannerIds(campaignId).get(1))
                        .getSitelinks();
        assertThat("быстрые ссылки соответствуют ожидаемым", actualSiteLinks,
                beanDiffer(siteLinksArray));
    }
}
