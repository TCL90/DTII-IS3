
package controllers.requests.brotherhood;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BrotherhoodService;
import services.MemberService;
import services.ProcessionService;
import services.RequestService;
import controllers.AbstractController;
import domain.Brotherhood;
import domain.Procession;
import domain.Request;

@Controller
@RequestMapping("/requests/brotherhood")
public class RequestBrotherhoodController extends AbstractController {

	@Autowired
	MemberService		memberService;

	@Autowired
	ProcessionService	processionService;

	@Autowired
	RequestService		requestService;

	@Autowired
	BrotherhoodService	brotherhoodService;


	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView res;
		final Brotherhood b = this.brotherhoodService.findOnePrincipal();
		final Collection<Procession> processions = this.processionService.findByBrotherhood(b);
		res = new ModelAndView("requests/brotherhood/list");
		res.addObject("processions", processions);
		res.addObject("requestURI", "requests/brotherhood/list.do");

		return res;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int processionId) {
		final ModelAndView res;
		final Procession p;

		final Collection<Request> requests = this.requestService.findByProcessionId(processionId);
		final Procession p1 = new Procession();
		p1.setId(processionId);
		p = this.processionService.findOne(p1);

		res = new ModelAndView("requests/brotherhood/show");
		res.addObject("procession", p);
		res.addObject("requests", requests);

		return res;
	}

	@RequestMapping(value = "/accept", method = RequestMethod.GET)
	public ModelAndView acceptRequest(@RequestParam final int requestIdA) {
		final ModelAndView res;
		final Request r;

		final Request r1 = new Request();
		r1.setId(requestIdA);
		r = this.requestService.findOne(r1);
		res = this.createEditModelAndView(r, "APPROVED");

		return res;
	}

	@RequestMapping(value = "/reject", method = RequestMethod.GET)
	public ModelAndView rejectRequest(@RequestParam final int requestIdR) {
		final ModelAndView res;
		final Request r;

		final Request r1 = new Request();
		r1.setId(requestIdR);
		r = this.requestService.findOne(r1);
		res = this.createEditModelAndView(r, "REJECTED");

		return res;
	}

	private ModelAndView createEditModelAndView(final Request r, final String status) {
		ModelAndView res;
		final List<Integer> li = new ArrayList<>(this.requestService.suggestPosition(r.getProcession()));
		res = new ModelAndView("requests/brotherhood/edit");

		res.addObject("row", li.get(0));
		res.addObject("column", li.get(1));
		res.addObject("request", r);
		res.addObject("status", status);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Request r, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors())
			res = this.createEditModelAndView(r, r.getStatus());
		else

			try {

				this.requestService.save(r);
				res = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(r, "error.request");
			}

		return res;
	}
}
