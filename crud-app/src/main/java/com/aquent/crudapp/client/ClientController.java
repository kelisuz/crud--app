package com.aquent.crudapp.client;
import com.aquent.crudapp.person.Person;
import com.aquent.crudapp.person.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for handling basic client management operations.
 */
@Controller
@RequestMapping("all/client")
public class ClientController {


    public static final String COMMAND_DELETE = "Delete";

    private final ClientService clientService;

    private final PersonService personService;

    public ClientController(ClientService clientService, PersonService personService) {
        this.clientService = clientService;
        this.personService = personService;
    }

    /**
     * Renders the listing page.
     *
     * @return list view populated with the current list of clients
     */
    @GetMapping(value = "list")
    public ModelAndView list() {
        List<Person> people = personService.listPeople();
        ModelAndView mav = new ModelAndView("all/client/list");
        mav.addObject("clients", clientService.listClient());
        mav.addObject("people", people);
        return mav;
    }
    /**
     * Renders an empty form used to create a new client record.
     *
     * @return create view populated with an empty client
     */

    @GetMapping(value = "create")
    public ModelAndView create() {
        List<Person> people = personService.listPeople();
        ModelAndView mav = new ModelAndView("all/client/create");
        mav.addObject("client", new Client());
        mav.addObject("errors", new ArrayList<String>());
        mav.addObject("people", people);
        return mav;
    }

    /**
     * Validates and saves a new client.
     * On success, the user is redirected to the listing page.
     * On failure, the form is redisplayed with the validation errors.
     *
     * @param client populated form bean for the client
     * @return redirect, or create view with errors
     */

    @PostMapping(value = "create")
    public ModelAndView create(Client client) {
        List<String> errors = clientService.validateClient(client);
        if (errors.isEmpty()) {
            clientService.createClient(client);
            return new ModelAndView("redirect:all/client/list");
        } else {
            ModelAndView mav = new ModelAndView("all/client/create");
            mav.addObject("client", client);
            mav.addObject("errors", errors);
            return mav;
        }
    }
    /**
     * Renders an edit form for an existing client record.
     *
     * @param clientId the ID of the client to edit
     * @return edit view populated from the client record
     */

    @GetMapping(value = "edit/{clientId}")
    public ModelAndView edit(@PathVariable Integer clientId) {
        List<Person> people = personService.listPeople();
        ModelAndView mav = new ModelAndView("/all/client/edit");
        mav.addObject("client", clientService.readClient(clientId));
        mav.addObject("errors", new ArrayList<String>());
        mav.addObject("people", people);
        return mav;
    }
    /**
     * Validates and saves an edited client.
     * On success, the user is redirected to the listing page.
     * On failure, the form is redisplayed with the validation errors.
     *
     * @param client populated form bean for the client
     * @return redirect, or edit view with errors
     */

    @PostMapping(value = "edit")
    public ModelAndView edit(Client client) {
        List<String> errors = clientService.validateClient(client);
        if (errors.isEmpty()) {
            clientService.updateClient(client);
            return new ModelAndView("redirect:/all/client/list");
        } else {
            ModelAndView mav = new ModelAndView("all/client/edit");
            mav.addObject("client", client);
            mav.addObject("errors", errors);
            return mav;
        }
    }

    /**
     * Renders the deletion confirmation page.
     *
     * @param clientId the ID of the client to be deleted
     * @return delete view populated from the client record
     */

    @GetMapping(value = "delete/{clientId}")
    public ModelAndView delete(@PathVariable Integer clientId) {
        ModelAndView mav = new ModelAndView("all/client/delete");
        mav.addObject("client", clientService.readClient(clientId));
        return mav;
    }


    /**
     * Handles client deletion or cancellation, redirecting to the listing page in either case.
     *
     * @param command the command field from the form
     * @param clientId the ID of the client to be deleted
     * @return redirect to the listing page
     */

    @PostMapping(value = "delete")
    public String delete(@RequestParam String command, @RequestParam Integer clientId) {
        if (COMMAND_DELETE.equals(command)) {
            clientService.deleteClient(clientId);
        }
        return "redirect:/all/client/list";
    }

}
