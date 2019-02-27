
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Request;

@Component
@Transactional
public class RequestToStringConverter implements Converter<Request, String> {

	@Override
	public String convert(final Request req) {
		String res;

		if (req == null)
			res = null;
		else
			//res = String.valueOf(Float);
			res = String.valueOf(req.getId());
		return res;
	}
}
