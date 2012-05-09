package views;

import com.petebevin.markdown.MarkdownProcessor;
import groovy.lang.Closure;
import play.templates.FastTags;
import play.templates.GroovyTemplate;
import play.templates.JavaExtensions;

import java.io.PrintWriter;
import java.util.Map;

/**
 * Extensions to render wiki syntax as HTML.
 */
public class TemplateExtensions extends JavaExtensions {

    public static String textile(String markup) {
        String html = new jj.play.org.eclipse.mylyn.wikitext.core.parser.MarkupParser(new jj.play.org.eclipse.mylyn.wikitext.textile.core.TextileLanguage()).parseToHtml(markup);
        html = html.substring(html.indexOf("<body>") + 6, html.lastIndexOf("</body>"));
        return html;
    }

    public static String markdown(String markup) {
        return new MarkdownProcessor().markdown(markup);
    }


}
