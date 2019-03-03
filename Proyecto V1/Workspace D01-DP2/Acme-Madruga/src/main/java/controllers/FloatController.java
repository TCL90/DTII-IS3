
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BrotherhoodService;
import services.FloatService;
import domain.Float;

@Controller
@RequestMapping("/float")
public class FloatController extends AbstractController {

	//Services
	@Autowired
	private FloatService		floatService;
	@Autowired
	private BrotherhoodService	brotherhoodService;


	//List
	//	@RequestMapping(value = "/brotherhood/list", method = RequestMethod.GET)
	//	public ModelAndView list() {
	//		ModelAndView res;
	//		Collection<Float> floats;
	//
	//		floats = this.floatService.findAll();
	//
	//		res = new ModelAndView("float/brotherhood/list");
	//		res.addObject("float", floats);
	//		res.addObject("requestURI", "float/brotherhood/list.do");
	//
	//		return res;
	//	}

	//	//Create and edit
	//	@RequestMapping(value = "/brotherhood/create", method = RequestMethod.GET)
	//	public ModelAndView create() {
	//		ModelAndView res;
	//		Float f;
	//
	//		f = this.floatService.create();
	//		res = this.createModelAndView(f);
	//
	//		return res;
	//	}

	//	@RequestMapping(value = "/brotherhood/edit", method = RequestMethod.GET)
	//	public ModelAndView edit(@RequestParam final int floatId) {
	//		ModelAndView res;
	//		Float f;
	//
	//		f = this.floatService.findOne(floatId);
	//		Assert.notNull(f);
	//		res = this.editModelAndView(f);
	//
	//		return res;
	//	}
	//
	//	//Save
	//	@RequestMapping(value = "/brotherhood/edit", method = RequestMethod.POST, params = "saveEdit")
	//	public ModelAndView saveEdit(@Valid final Float f, final BindingResult binding) {
	//		ModelAndView res;
	//
	//		if (binding.hasErrors())
	//			res = this.editModelAndView(f);
	//		else
	//			try {
	//				this.floatService.save(f);
	//				res = new ModelAndView("redirect:list.do");
	//			} catch (final Throwable oops) {
	//				res = this.editModelAndView(f, "float.commit.error");
	//			}
	//		return res;
	//	}
	//
	//	@RequestMapping(value = "/brotherhood/create", method = RequestMethod.POST, params = "saveCreate")
	//	public ModelAndView saveCreate(@Valid final Float f, final BindingResult binding) {
	//		ModelAndView res;
	//
	//		if (binding.hasErrors())
	//			res = this.createModelAndView(f);
	//		else
	//			try {
	//				this.floatService.save(f);
	//				res = new ModelAndView("redirect:list.do");
	//			} catch (final Throwable oops) {
	//				res = this.editModelAndView(f, "float.commit.error");
	//			}
	//		return res;
	//	}

	//	//Delete
	//	@RequestMapping(value = "/brotherhood/edit", method = RequestMethod.POST, params = "delete")
	//	public ModelAndView delete(final Float f, final BindingResult binding) {
	//		ModelAndView res;
	//
	//		try {
	//			this.floatService.delete(f);
	//			res = new ModelAndView("redirect:list.do");
	//		} catch (final Throwable oops) {
	//			res = this.editModelAndView(f, "float.commit.error");
	//		}
	//		return res;
	//	}

	//	//Show
	//	@RequestMapping(value = "/brotherhood/show", method = RequestMethod.GET)
	//	public ModelAndView show(@RequestParam final int floatId) {
	//		ModelAndView res;
	//		Float f;
	//
	//		res = new ModelAndView("float/brotherhood/show");
	//		f = this.floatService.findOne(floatId);
	//		res.addObject("float", f);
	//		res.addObject("requestURI", "float/brotherhood/show.do");
	//
	//		return res;
	//	}

	//	protected ModelAndView editModelAndView(final Float f) {
	//		ModelAndView res;
	//		res = this.editModelAndView(f, null);
	//		return res;
	//	}
	//
	//	protected ModelAndView editModelAndView(final Float f, final String messageCode) {
	//		ModelAndView res;
	//
	//		res = new ModelAndView("float/brotherhood/edit");
	//		res.addObject("float", f);
	//		res.addObject("brotherhoods", this.brotherhoodService.findAll());
	//		res.addObject("message", messageCode);
	//
	//		return res;
	//	}

	//	protected ModelAndView createModelAndView(final Float f) {
	//		ModelAndView res;
	//		res = this.createModelAndView(f, null);
	//		return res;
	//	}
	//
	//	protected ModelAndView createModelAndView(final Float f, final String messageCode) {
	//		ModelAndView res;
	//
	//		res = new ModelAndView("float/brotherhood/create");
	//		res.addObject("float", f);
	//		res.addObject("brotherhoods", this.brotherhoodService.findAll());
	//		res.addObject("message", messageCode);
	//
	//		return res;
	//	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int brotherhoodId) {
		final ModelAndView res;

		final Collection<Float> floats = this.floatService.findByBrotherhoodId(brotherhoodId);

		res = new ModelAndView("float/list");
		res.addObject("requestURI", "float/list.do");
		res.addObject("floats", floats);
		return res;
	}

}
