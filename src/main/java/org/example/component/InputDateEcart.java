package org.example.component;

import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.inputnumber.InputNumber;

import javax.faces.component.FacesComponent;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIInput;
import javax.faces.component.UINamingContainer;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

@FacesComponent("inputDateEcart")
public class InputDateEcart extends UIInput implements NamingContainer {
    private final DateTimeFormatter jourFormatter = DateTimeFormatter.ofPattern("EEEE");
    private InputNumber interval;
    private Calendar date;
    private HtmlOutputText weekDay;

    @Override
    public String getFamily() {
        return UINamingContainer.COMPONENT_FAMILY;
    }

    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        LocalDate value = (LocalDate) getValue();

        if (value != null) {
            this.date.setValue(DateTimeFormatter.ofPattern(this.date.getPattern()).format(value));
            weekDay.setValue(getWeekDayString(value));
            interval.setValue(ChronoUnit.DAYS.between(getReference(), value));
        }

        super.encodeBegin(context);
    }

    @Override
    public Object getSubmittedValue() {
        return date.getSubmittedValue();
    }

    public void updateDate(AjaxBehaviorEvent event) {
        Long ecart = Long.parseLong((String) this.interval.getValue());
        LocalDate date = (ecart == null) ? null : getReference().plus(ecart, ChronoUnit.DAYS);
        this.date.setValue(DateTimeFormatter.ofPattern(this.date.getPattern()).format(date));
        if (date != null) weekDay.setValue(getWeekDayString(date));
    }

    public void updateInterval(AjaxBehaviorEvent event) {
        LocalDate date = (LocalDate) this.date.getValue();
        if (date == null) {
            weekDay.setValue("");
            interval.setValue("");
        } else {
            weekDay.setValue(getWeekDayString(date));
            interval.setValue(ChronoUnit.DAYS.between(getReference(), date));
        }
    }

    private LocalDate getReference() {
        return (LocalDate) getAttributes().get("reference");
    }

    private String getWeekDayString(LocalDate date) {
        return "days, " + jourFormatter.format(date);
    }

    public InputNumber getInterval() {
        return interval;
    }

    public void setInterval(InputNumber interval) {
        this.interval = interval;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public HtmlOutputText getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(HtmlOutputText weekDay) {
        this.weekDay = weekDay;
    }
}
