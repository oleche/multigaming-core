package multigaming.core.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.validation.Validated;

@Validated
@Controller("/action")
public class ActionController {
    @Get("/")
    public String showAll(){
        return "hola";
    }
}
