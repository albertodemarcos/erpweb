import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
// FullCalendar v5
import {CalendarOptions /*, DateSelectArg*/, EventSourceInput,
        EventInput, EventClickArg, EventApi, EventAddArg } from '@fullcalendar/angular';
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
  public eventosCalendario: EventApi[] = [];
  private eventAddArg: EventAddArg;
  private inicioEventosCalendario: any;
  private eventSourceInput: EventSourceInput;

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

// Iniciar Configuracion Calendario
 initFullCalendar(): void{

  console.log('Iniciamos la configuracion del calendario');

  this.configuracionCalendario = {
    headerToolbar: {
      left: 'prev,next today btnCrearEventoButton',
      center: 'title',
      right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek'
    },
    initialView: 'dayGridMonth',
    events: (info, successCallback, failureCallback) => {

      this.inicioEventosCalendario = new Array<EventInput>();

      this.eventoService.getEventos().then(
        (eventos) => {
          try {
            // Introducimos los datos
            eventos.forEach(evento => {
              console.log('Entramos a setear el evento');
              const eventoId = evento.id;
              const eventoTitle = evento.titulo;
              const eventoStart = evento.fechaInicio;
              const eventoEnd = evento.fechaFin;
              this.inicioEventosCalendario.push({
                id: eventoId,
                title: eventoTitle,
                start: eventoStart,
                end: eventoEnd
              });
              console.log('Terminamos de setear el evento');
            });
            successCallback(this.inicioEventosCalendario);
            console.log('SALIMOS en el metodo getListadoEventos()');
          } catch (errores){
            console.error('Se ha producido un error al convertir la infomracion del servidor' + errores);
            failureCallback(this.inicioEventosCalendario);
          }
        }, (error) => {
          console.log('Error, no se ha obtenido la informacion');
          failureCallback(this.inicioEventosCalendario);
        }
      );
    },
    weekends: true,
    editable: true,
    selectable: false,
    selectMirror: true,
    dayMaxEvents: true,
    locales: [ esLocale, enLocale, frLocale ],
    locale: 'es',
    // select: this.crearEventoPlanificador.bind(this), // Seleccionar
    eventClick: this.getEvento.bind(this), // Click evento
    eventsSet: this.recargarCalendario.bind(this), // SIEMPRE QUE OCURRA ALGO EN EL PLANNING
    // eventAdd: () => { this.iniciarEventos(); },
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
      // Informamos de que se ha creado el evento correctamente 

      // Reload

      // Se cierra la modal de crear evento
      this.ocultarModalCrearEvento();

    }
  });
}

editarEventoPlanificador(): void {

}

eliminarEventoPlanificador(): void {

}

getEvento(eventoSeleccionado: EventClickArg): void {

  console.log('Se procede a recuperar el evento');

  const eventoClick = eventoSeleccionado.event;

  if (eventoClick != null && eventoClick.id.toString() !== '0' && eventoClick.id.toString() !== '-1'  ){

    // Recuperamos el evento
    const id: number = +eventoClick.id;
    this.evento.id = id;
    this.evento.titulo = eventoClick.title;

    console.log('FECHA INICIO: ' + eventoClick.start);

    this.evento.fechaInicio = eventoClick.start;

    console.log('FECHA FIN: ' + eventoClick.end);

    if ( eventoClick.end != null ){
      this.evento.diaCompleto = false;
      this.evento.fechaFin = eventoClick.end;
    }else{
      this.evento.diaCompleto = true;
    }

    this.evento.descripcion = '¡Esto es un prueba!';
    // Rellenamos el objeto evento

    // Mostrarmos la modal con el evento recuperado
    this.mostrarModalGetEvento();

  }



}

recargarCalendario(events: EventApi[]): void{

  console.log('Entramos en el metodo getListadoEventos()');

  // Contenedor de eventos iniciales al cargar el planificador
  this.inicioEventosCalendario = new Array<EventApi>();

  this.eventoService.getEventos().then(
    (eventos) => {
      try {
        // Introducimos los datos
        eventos.forEach(evento => {
          // Introducimos los eventos en el array
          this.inicioEventosCalendario.push({
            id: evento.id,
            title: evento.titulo,
            start: evento.fechaInicio,
            end: evento.fechaFin
          });
        });
        console.log('SALIMOS en el metodo getListadoEventos()');
        return this.inicioEventosCalendario;
      } catch (errores){
        console.error('Se ha producido un error al convertir la infomracion del servidor' + errores);
        return this.inicioEventosCalendario;
      }
    }, (error) => {
      console.log('Error, no se ha obtenido la informacion');
      return this.inicioEventosCalendario;
    }
  );

}



/* METODOS AUXLIARES */

mostrarModalCrearEvento(): void {
  $('#crearEventoModal').modal('show');
}

ocultarModalCrearEvento(): void {
  $('#crearEventoModal').modal('hide');
}

mostrarModalGetEvento(): void {
  $('#visualizarEventoModal').modal('show');
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

  // OTROS METODOS

// Inicio Eventos del Calendario
 iniciarEventos(): any{

  console.log('Entramos en el metodo getListadoEventos()');

  // Contenedor de eventos iniciales al cargar el planificador
  this.inicioEventosCalendario = new Array<EventSourceInput>();

  this.eventoService.getEventos().then(
    (eventos) => {
      try {
        // Introducimos los datos
        eventos.forEach(evento => {
          // Introducimos los eventos en el array
          this.inicioEventosCalendario.push({
            id: evento.id,
            title: evento.titulo,
            start: evento.fechaInicio,
            end: evento.fechaFin
          });
        });
        console.log('SALIMOS en el metodo getListadoEventos()');
        return this.inicioEventosCalendario;
      } catch (errores){
        console.error('Se ha producido un error al convertir la infomracion del servidor' + errores);
        return this.inicioEventosCalendario;
      }
    }, (error) => {
      console.log('Error, no se ha obtenido la informacion');
      return this.inicioEventosCalendario;
    }
  );
}
[
    { id: '1',  title: 'Evento prueba', start: '2020-06-28 15:00', end: '2020-06-28 18:00' },
    { id: '1',  title: 'Evento prueba', start: '2020-06-28', end: '2020-06-28' }
  ]



}*/
