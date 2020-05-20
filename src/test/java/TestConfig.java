import cn.madf.YmlConfig.YmlConfigModel;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

/**
 * @author 烛影鸾书
 * @date 2020/4/23
 * @copyright© 2020
 */
public class TestConfig {
    public static void main(String[] args) {
        InputStream is = ClassLoader.getSystemResourceAsStream("TestConfig.yml");
        YmlConfigModel config = new YmlConfigModel();
        if (is != null) {
            Yaml yaml = new Yaml();
            config = yaml.loadAs(is, YmlConfigModel.class);
        }
    }
}
