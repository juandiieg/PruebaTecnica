package tasks;

import net.serenitybdd.screenplay.actions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.containsText;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.hasValue;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;
import static userInterface.RegisterStep1.*;


public class ingresarDatosPersonales implements Task{

	private String nombre;
	private String apellido;
	private String correo;
	private String mes;
	private String dia;
	private String anio;
	private String idioma;

	

	public ingresarDatosPersonales(String nombre, String apellido, String correo, String mes, String dia, String anio,
                                   String idioma) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.mes = mes;
		this.dia = dia;
		this.anio = anio;
		this.idioma = idioma;

	}

	public <T extends Actor> void performAs(T actor) {
		actor.attemptsTo(
				Enter.theValue(nombre).into(INP_FIRSTNAME),//Diligencia el nombre
				Enter.theValue(apellido).into(INP_LASTNAME),//Diligencia el apellido
				Enter.theValue(correo).into(INP_EMAIL),//Diligencia email
				SelectFromOptions.byVisibleText(mes).from(SLC_MONTH),//Selecciona el mes de nacimiento
				SelectFromOptions.byVisibleText(dia).from(SLC_DAY),//Selecciona el día de nacimiento
				SelectFromOptions.byVisibleText(anio).from(SLC_YEAR),//Selecciona el año de nacimiento
				Enter.theValue(idioma).into(INP_LANGUAGE).thenHit(Keys.ENTER)//Diligencia idioma
				);
		actor.should(
				seeThat(the(INP_FIRSTNAME), hasValue(nombre)),
				seeThat(the(INP_LASTNAME), hasValue(apellido)),
				seeThat(the(INP_EMAIL), hasValue(correo)),
				seeThat(the(SLC_MONTH), containsText(mes)),
				seeThat(the(SLC_DAY), containsText(dia)),
				seeThat(the(SLC_YEAR), containsText(anio))
//				seeThat(the(By.xpath("//span[contains(text(),'Spanish')]")), hasValue(idioma))
		);
		actor.attemptsTo(
				Click.on(BTN_NEXT)
		);
	}

	public static ingresarDatosPersonales DiligenciarFormulario(String nombre, String apellido, String correo, String mes, String dia,
																String anio, String idioma) {

		return Instrumented.instanceOf(ingresarDatosPersonales.class).withProperties(nombre, apellido, correo, mes, dia, anio,
				idioma);
	}
}
