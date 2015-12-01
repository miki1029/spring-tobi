package springbook.learningtest.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by miki on 15. 10. 29..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/junit.xml")
public class JUnitTest {
    @Autowired ApplicationContext context;

    static Set<JUnitTest> testObjects = new HashSet<JUnitTest>();
    static ApplicationContext contextObject = null;

    @Test public void test1() {
        // 테스트 케이스 별로 새로운 오브젝트를 만드는지 테스트
        assertThat(testObjects, not(hasItem(this)));
        testObjects.add(this);

        // 애플리케이션 컨텍스트를 공유하는지 테스트
        assertThat(contextObject == null || contextObject == this.context, is(true));
        contextObject = this.context;
    }

    @Test public void test2() {
        assertThat(testObjects, not(hasItem(this)));
        testObjects.add(this);

        assertThat(contextObject == null || contextObject == this.context, is(true));
        contextObject = this.context;
    }

    @Test public void test3() {
        assertThat(testObjects, not(hasItem(this)));
        testObjects.add(this);

        assertThat(contextObject == null || contextObject == this.context, is(true));
        contextObject = this.context;
    }
}
