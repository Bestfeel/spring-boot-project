package com.github.config;

import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;
import org.apache.ibatis.session.Configuration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * LanguageDriver，在MyBatis编译语句前，将我们自定义的标签替换为了动态SQL语句
 * 等价于下面的语句
 *
 * <code>
 *     @Select({"<script>",
 *          "SELECT *",
 *          "FROM user",
 *          "WHERE id IN",
 *            "<foreach item='item' index='index' collection='list'",
 *              "open='(' separator=',' close=')'>",
 *              "#{item}",
 *            "</foreach>",
 *          "</script>"})
 * </code>
 */
public class SimpleSelectInExtendedLanguageDriver extends XMLLanguageDriver implements LanguageDriver {

    private final Pattern inPattern = Pattern.compile("\\(#\\{(\\w+)\\}\\)");

    @Override
    public SqlSource createSqlSource(Configuration configuration,
                                     String script, Class<?> parameterType) {

        Matcher matcher = inPattern.matcher(script);
        if (matcher.find()) {
            script = matcher.replaceAll("(<foreach collection=\"$1\" item=\"__item\" separator=\",\" >#{__item}</foreach>)");
        }

        script = "<script>" + script + "</script>";
        return super.createSqlSource(configuration, script, parameterType);
    }
}
