package helloworldrestlet;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

import org.restlet.Context;
import org.restlet.data.MediaType;
import org.restlet.data.Request;
import org.restlet.data.Response;
import org.restlet.resource.Representation;
import org.restlet.resource.Resource;
import org.restlet.resource.StringRepresentation;
import org.restlet.resource.Variant;
import org.restlet.util.Template;

/**
 * A simple resource with two representations.
 * 
 */
public class HelloWorldResource extends Resource {

	/**
	 * Formating template for HTML representation of this resource.
	 */
	private static Template htmlTemplate = new Template(
			"<html><body><h1>Hello World!</h1><p>It is now {DATE}.</p></body></html>");

	/**
	 * Formating template for text representation of this resource.
	 */
	private static Template textTemplate = new Template(
			"Hello, world! It is now {DATE}.");

	@Override
	public void init(Context context, Request request, Response response) {
		super.init(context, request, response);

		// This representation has only two types of representations.
		getVariants().add(new Variant(MediaType.TEXT_HTML));
		getVariants().add(new Variant(MediaType.TEXT_PLAIN));
	}

	/**
	 * Returns a full representation for a given variant.
	 */
	@Override
	public Representation represent(Variant variant) {
		final Map<String, Object> variables = Collections.singletonMap("DATE",
				(Object) new Date());
		if (MediaType.TEXT_HTML.equals(variant.getMediaType())) {
			return new StringRepresentation(htmlTemplate.format(variables),
					MediaType.TEXT_HTML);
		}
		return new StringRepresentation(textTemplate.format(variables),
				MediaType.TEXT_PLAIN);
	}
}
