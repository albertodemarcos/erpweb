import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
// FullCalendar v5
import { CalendarOptions /*, DateSelectArg, EventClickArg*/, EventApi, EventAddArg } from '@fullcalendar/angular';
import esLocale from '@fullcalendar/core/locales/es';
import enLocale from '@fullcalendar/core/locales/en-gb';
import frLocale from '@fullcalendar/core/locales/fr';
// Propios
import { EventoService } from 'src/app/services/planificador/evento.service';
import { Evento } from 'src/app/model/entitys/evento.model';
import { Event } from 'src/app/model/entitys/event.model';



declare var $: any;



@Component({
  selector: 'app-planificador',
  templateUrl: './planificador.component.html',
  styleUrls: ['./planificador.component.css']
})
export class PlanificadorComponent implements OnInit  {

  // Configuracion calendario
  public calendarioVisible: boolean;
  public configuracionCalendario: CalendarOptions;

  // Evento
  public evento: Evento;
  private eventAddArg: EventAddArg;


  currentEvents: EventApi[] = [];

  constructor(private eventoService: EventoService, private router: Router) {

    console.log('Constructor()');

    this.calendarioVisible = true;
    this.evento = new Evento();

    this.initFullCalendar();

  }

  ngOnInit(): void {

    console.log('ngOnInit()');

  }


/* METODOS GENERALES */

 initFullCalendar(): void{

  console.log('Iniciamos la configuracion del calendario');

  this.configuracionCalendario = {
    headerToolbar: {
      left: 'prev,next today btnCrearEventoButton',
      center: 'title',
      right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek'
    },
    initialView: 'dayGridMonth',
    // initialEvents: INITIAL_EVENTS, // alternatively, use the `events` setting to fetch from a feed
    weekends: true,
    editable: true,
    selectable: false,
    selectMirror: true,
    dayMaxEvents: true,
    locales: [ esLocale, enLocale, frLocale ],
    locale: 'es',
    // select: this.crearEventoPlanificador.bind(this), // Seleccionar
    // eventClick: this.handleEventClick.bind(this), // Click evento
    // eventsSet: this.handleEvents.bind(this) // Setear eventos actuales
    // eventAdd: () => { this.crearEventoPlanificador(); }
    // eventChange:
    // eventRemove:
    customButtons: {
      // Añadir evento
      btnCrearEventoButton: {
        // Texto del evento
        text: 'Crear evento',
        click: () => {
          console.log('Pulsado el boton crear evento');
          this.mostrarModalCrearEvento();
          // Mostramos todos los eventos

        }
      }
    },
    // Style
    height: 500
  };

 }

 // Crear evento
 crearEventoPlanificador(): void {

  console.log('Comienza la persistencia del evento' + JSON.stringify(this.evento) );

  this.eventoService.crearEvento(this.evento).subscribe( accionRespuesta => {
    console.log('Esta registrado' + accionRespuesta.resultado);
    console.log('Datos que nos devuelve spring: ' + JSON.stringify(accionRespuesta));
    // Si el resultado es true, navegamos hasta la vista
    if (accionRespuesta.resultado && accionRespuesta.id !== null ) {

      console.log('Evento creado correctamente');
    }
  });
}

mostrarModalCrearEvento(): void {
  $('#crearEventoModal').modal('show');
}



}

/*import { Component, OnInit, ViewChild, ElementRef, EventEmitter } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
// FullCalendar v4
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGrigPlugin from '@fullcalendar/timegrid';
import interactionPlugin, { Draggable } from '@fullcalendar/interaction';
import { FullCalendarComponent, FullCalendarModule } from '@fullcalendar/angular';
import resourceTimeGridPlugin from '@fullcalendar/resource-timegrid';
import esLocale from '@fullcalendar/core/locales/es';
import enLocale from '@fullcalendar/core/locales/en-gb';
import frLocale from '@fullcalendar/core/locales/fr';

// Propios
import { ModalEventoComponent } from 'src/app/components/modales/planificador/modal-evento/modal-evento.component';

import { EventoService } from 'src/app/services/planificador/evento.service';
import { Evento } from 'src/app/model/entitys/evento.model';

declare var $: any;

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

  public evento: Evento;
  private fullCalendarData: Evento[];

  constructor(private eventoService: EventoService, private router: Router) {
    this.eventosCalendario = [{title: 'Evento 1', date: '2020-06-24'}, {title: 'Evento 2', date: '2020-06-25'}];
    this.evento = new Evento();

    this.fullCalendarData = new Array<Evento>();
  }

  ngOnInit(): void {

    this.getListadoEventos();

    console.log('Datos: ' + JSON.stringify(this.fullCalendarData) );

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
      eventosCalendario: this.fullCalendarData,
      // Boton crear evento
      customButtons: {
        // Añadir evento
        btnCrearEventoButton: {
          // Texto del evento
          text: 'Crear evento',
          click: () => {
            console.log('Pulsado el boton crear evento');
            this.mostrarModalCrearEvento();
            // Mostramos todos los eventos
            this.getListadoEventos();
          }
        }
      },
      eventRender: () => {

      }
    };
  }


  mostrarModalCrearEvento(): void {
    $('#crearEventoModal').modal('show');
  }

  crearEventoPlanificador(): void {

    console.log('Comienza la persistencia del evento' + JSON.stringify(this.evento) );

    this.eventoService.crearEvento(this.evento).subscribe( accionRespuesta => {
      console.log('Esta registrado' + accionRespuesta.resultado);
      console.log('Datos que nos devuelve spring: ' + JSON.stringify(accionRespuesta));
      // Si el resultado es true, navegamos hasta la vista
      if (accionRespuesta.resultado && accionRespuesta.id !== null ) {
        this.getListadoEventos();
      }
    });
  }

  getListadoEventos(): void{

    console.log('Entramos en el metodo getListadoEventos()');

    this.eventoService.getEventos().then(
      (eventos) => {
        try {
          // Introducimos los datos
          eventos.forEach(evento => this.fullCalendarData.push(evento));
          // Refrescamos los datos del calendario
          this.fullcalendar.events = this.fullCalendarData;

        } catch (errores){
          console.error('Se ha producido un error al convertir la infomracion del servidor' + errores);
        }
      }, (error) => {
        console.log('Error, no se ha obtenido la informacion');
      }
    );

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



}*/
