import com.chinaums.commons.utils.DateUtils;
import org.junit.Test;

/**
 * @author lyq
 * @time 2019/10/16 14:29
 */
public class MyTest {

    @Test
    public void testIsDate(){
        System.out.println(DateUtils.isDate("20191016","yyyyMMdd"));
        System.out.println(DateUtils.isDate("20191032","yyyyMMdd"));
        System.out.println(DateUtils.isDate("201995","yyyyMMdd"));
        System.out.println(DateUtils.isDate("20191016 14:31:00","yyyyMMdd"));
        System.out.println(DateUtils.isDate("20190229","yyyyMMdd"));
        System.out.println(DateUtils.isDate("","yyyyMMdd"));
    }
}
