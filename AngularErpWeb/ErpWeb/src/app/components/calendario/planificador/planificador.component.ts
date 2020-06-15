import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGrigPlugin from '@fullcalendar/timegrid';
import interactionPlugin, { Draggable } from '@fullcalendar/interaction';
import { FullCalendarComponent } from '@fullcalendar/angular';
import resourceTimeGridPlugin from '@fullcalendar/resource-timegrid';
import esLocale from '@fullcalendar/core/locales/es';
import enLocale from '@fullcalendar/core/locales/en-gb';
import frLocale from '@fullcalendar/core/locales/fr';



@Component({
  selector: 'app-planificador',
  templateUrl: './planificador.component.html',
  styleUrls: ['./planificador.component.css']
})
export class PlanificadorComponent implements OnInit  {

  @ViewChild('planificador') fullcalendar: FullCalendarComponent;
  @ViewChild('external') external: ElementRef;

  public configuracionCalendario: any;
  public eventosCalendario: any;

  constructor() {
    this.eventosCalendario = [{title: 'Evento 1', date: '2020-06-15'}, {title: 'Evento 1', date: '2020-06-16'}];
  }

  ngOnInit(): void {

    // Iniciamos las propiedades del calendario
    this.configuracionCalendario = {
      // Dia, mes, semana, etc..
      headerCalendario: {
        left: 'prev,next today btnCrearEventoButton',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek'
      },
      // Se puede editar
      editable: true,
      // Distintos idiomas
      locales: [ esLocale, enLocale, frLocale ],
      // Idoma español por defecto
      locale: 'es',
      // Plugins
      calendarPlugins: [dayGridPlugin, timeGrigPlugin, interactionPlugin],
      // Eventos
      eventosCalendario: [{title: 'Evento 1', date: '2020-06-15'}, {title: 'Evento 1', date: '2020-06-16'}],
      // Boton crear evento
      customButtons: {
        // Añadir evento
        btnCrearEventoButton: {
          // Texto del evento
          text: 'Crear evento',
          click: () => {
            console.log('Pulsado el boton crear evento');
            this.crearEventoPlanificador();
          }
        }
      }
    };
  }

  crearEventoPlanificador(): void {
    alert('Evento creado!');
  }

  updateHeader() {
    this.configuracionCalendario.header = {
      left: 'prev,next,today  btnCrearEventoButton',
      center: 'title',
      right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek'
    };
  }
  get yearMonth(): string {
    const dateObj = new Date();
    return dateObj.getUTCFullYear() + '-' + (dateObj.getUTCMonth() + 1);
  }



}
