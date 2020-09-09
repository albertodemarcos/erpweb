import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
// FullCalendar v5
import {CalendarOptions, EventSourceInput,
        EventInput, EventClickArg, EventApi, EventAddArg, EventChangeArg } from '@fullcalendar/angular';
import esLocale from '@fullcalendar/core/locales/es';
import enLocale from '@fullcalendar/core/locales/en-gb';
import frLocale from '@fullcalendar/core/locales/fr';
// Swat alet
import swal from 'sweetalert2';

// Propios
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import { EventoService } from 'src/app/services/planificador/evento.service';
import { Evento } from 'src/app/model/entitys/evento.model';

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
  private eventoId: number;
  private eventoDto: any;
  public eventosCalendario: EventApi[] = [];
  private inicioEventosCalendario: any;
  private respuestaGetCliente: AccionRespuesta;
  public tituloCrearEditar: string;
  public botonCrearEditar: string;

  constructor(private eventoService: EventoService, private router: Router) {
    this.calendarioVisible = true;
    this.evento = new Evento();
    this.tituloCrearEditar = 'Crear evento';
    this.botonCrearEditar = 'Crear';
    this.initFullCalendar();
  }

  ngOnInit(): void {
  }

  /* METODOS GENERALES */

  // Iniciar Configuracion Calendario
  initFullCalendar(): void{
    this.configuracionCalendario = {
      headerToolbar: {
        left: 'prevYear prev next nextYear today btnCrearEventoButton',
        center: 'title',
        right: 'dayGridMonth timeGridWeek timeGridDay listWeek'
      },
      initialView: 'dayGridMonth',
      events: (info, successCallback, failureCallback) => {
        this.inicioEventosCalendario = new Array<EventInput>();
        this.eventoService.getEventos().then(
          (eventos) => {
            try {
              // Introducimos los datos
              eventos.forEach(evento => {
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
              });
              successCallback(this.inicioEventosCalendario);
            } catch (errores){
              console.error('Se ha producido un error al convertir la infomracion del servidor' + errores);
              failureCallback(this.inicioEventosCalendario);
            }
          }, (error) => {
            console.error('Error, no se ha obtenido la informacion');
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
      eventTextColor: '#000',
      eventColor: '#0066ff',
      eventBackgroundColor: '#80ccff',
      // select: this.crearEventoPlanificador.bind(this), // Seleccionar
      eventClick: this.getEvento.bind(this), // Click evento
      // eventsSet: this.recargarCalendario.bind(this), // SIEMPRE QUE OCURRA ALGO EN EL PLANNING
      // eventAdd: () => { this.iniciarEventos(); },
      eventChange: (eventoDrag: EventChangeArg) => {
        this.actualizaEventoDrag(eventoDrag);
      },
      // eventRemove:
      customButtons: {
        // Añadir evento
        btnCrearEventoButton: {
          // Texto del evento
          text: 'Crear evento',
          click: () => {
            this.tituloCrearEditar = 'Crear evento';
            this.botonCrearEditar = 'Crear';
            this.limpiarModalCrearEvento();
            this.mostrarModalCrearEvento();
          }
        }
      },
      // Style
      height: 500
    };
  }

  // Crear evento
  crearEventoPlanificador(): void {

    if ( !this.comprobarFormularioCrearEvento())
    {
      return;
    }

    this.eventoService.crearEvento(this.evento).subscribe( accionRespuesta => {
      // Si el resultado es true, navegamos hasta la vista
      if (accionRespuesta.resultado && accionRespuesta.id !== null ) {
        // Recargamos los eventos
        this.recargarCalendario();
        // Limpiamos la modal
        this.limpiarModalCrearEvento();
        // Informamos de que se ha creado el evento correctamente
        swal('Evento', 'Se ha creado el evento', 'success');
        // Se cierra la modal de crear evento
        this.ocultarModalCrearEvento();
      }
    });
  }

  // Editar evento
  editarEventoPlanificador(): void {
    // Cambiamos nombre y boton de la modal porque es editar
    this.tituloCrearEditar = 'Editar evento';
    this.botonCrearEditar = 'Editar';
    // Ocultamos la modal de visualizacion
    this.ocultarModalGetEvento();
    // Mostramos la modal de crear evento
    this.mostrarModalCrearEvento();
  }

  // Elimnar evento
  eliminarEventoPlanificador(): void {
    // Evitamos borrar accidentalmente un evento
    swal({
      title: 'Eliminar evento',
      text: '¿Desea eliminar definitivamente este evento?',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí',
      cancelButtonText: 'No'
    }).then( (resultado) => {
      // Si se pulsa en cancelar, no se continua
      if (!resultado.value) {
        return;
      }

      // Llamamos al servicio de Eventos para eliminar el Evento
      this.eventoService.eliminarEvento(this.evento.id).toPromise().then( (accionRespuesta) => {

        // Si se ha eliminado correctamente
        if ( accionRespuesta.resultado ) {
        swal('Evento elimninado', 'Se ha eliminado el evento correctamente', 'success').then(() => {
          this.recargarCalendario();
          this.ocultarModalGetEvento();
        });

        } else {
        console.log('Se ha producido un error al eliminar el evento');
        swal('Error', 'El Evento no ha podido ser eliminado', 'error');
        }

      }, (errores) => {
        console.log('Se ha producido un error al eliminar el Evento');
        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');
      } );
    } );
  }

  // Obtener evento
  getEvento(eventoSeleccionado: EventClickArg): void {
    const eventoClick = eventoSeleccionado.event;
    if (eventoClick != null && eventoClick.id.toString() !== '0' && eventoClick.id.toString() !== '-1'  ){
      // Recuperamos el ID del evento
      this.eventoId = +eventoClick.id;
      // Recuperamos el evento
      this.eventoService.getEvento(this.eventoId).toPromise().then( (accionRespuesta) => {
        try
        {
          this.respuestaGetCliente = accionRespuesta;
          if ( this.respuestaGetCliente.resultado )
          {
            // tslint:disable-next-line: no-string-literal
            this.eventoDto = this.respuestaGetCliente.data['eventoDto'];
            // Rellenamos el objeto evento
            this.obtenerEventoDesdeEventoDto(this.eventoDto);
            // Mostrarmos la modal con el evento recuperado
            this.mostrarModalGetEvento();
          }
        }catch (errores){
          console.error('Se ha producido un error al transformar el evento' + errores);
          swal('Error', 'Se ha producido un error renderizar el evento', 'error');
        }
      }, (error) => {
        console.error('Error, no se ha podido recuperar el evento' + error);
        swal('Error', 'Error, no se ha podido recuperar el evento', 'error');
      });
    }
  }

  // Recargar eventos del calendario
  recargarCalendario(): void{
    this.configuracionCalendario.eventSources = [];
    this.configuracionCalendario.eventSources.push({
      events: (info, successCallback, failureCallback) => {
        this.inicioEventosCalendario = new Array<EventInput>();
        this.eventoService.getEventos().then(
          (eventos) => {
            try {
              // Introducimos los datos
              eventos.forEach(evento => {
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
              });
              successCallback(this.inicioEventosCalendario);
            } catch (errores){
              console.error('Se ha producido un error al convertir la infomracion del servidor' + errores);
              failureCallback(this.inicioEventosCalendario);
            }
          }, (error) => {
            console.error('Error, no se ha obtenido la informacion');
            failureCallback(this.inicioEventosCalendario);
          }
        );
      }
    });
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

  ocultarModalGetEvento(): void {
    $('#visualizarEventoModal').modal('hide');
  }

  // Obtener evento antes de guardar desde un movimento en el calendario
  actualizaEventoDrag(eventoDrag: EventChangeArg): void {
    const eventoClick = eventoDrag.event;
    if (eventoClick != null && eventoClick.id.toString() !== '0' && eventoClick.id.toString() !== '-1'  ){
      // Recuperamos el ID del evento
      this.eventoId = +eventoClick.id;
      // Recuperamos el evento
      this.eventoService.getEvento(this.eventoId).toPromise().then( (accionRespuesta) => {
        try
        {
          this.respuestaGetCliente = accionRespuesta;
          if ( this.respuestaGetCliente.resultado )
          {
            // tslint:disable-next-line: no-string-literal
            this.eventoDto = this.respuestaGetCliente.data['eventoDto'];
            // Rellenamos el objeto evento
            this.obtenerEventoDesdeEventoDto(this.eventoDto);
            // Actualizamos el evento recuperado
            this.evento.fechaInicio = eventoDrag.event.start;
            this.evento.fechaFin = eventoDrag.event.end;
            // Actualizamos el evento
            this.crearEventoPlanificador();
          }
        }catch (errores){
          console.error('Se ha producido un error al transformar el evento' + errores);
          swal('Error', 'Se ha producido un error en el evento cambiado', 'error');
        }
      }, (error) => {
        console.error('Error, no se ha podido recuperar el evento' + error);
        swal('Error', 'Error, no se ha podido recuperar el evento', 'error');
      });
    }
  }

  // Obtener el eventoDto
  obtenerEventoDesdeEventoDto(eventoDto: any): void{
    if ( eventoDto != null)
    {
      this.evento.id = eventoDto.id;
      this.evento.titulo = eventoDto.titulo;
      this.evento.fechaInicio = this.limpiarFecha(eventoDto.fechaInicio);
      this.evento.fechaFin = this.limpiarFecha(eventoDto.fechaFin);
      this.evento.descripcion = eventoDto.descripcion;
    }
  }

  // Limpiamos el objeto evento
  limpiarModalCrearEvento(): void {
    this.evento.titulo = null;
    this.evento.fechaInicio = null;
    this.evento.fechaFin = null;
    this.evento.descripcion = null;
  }

  // Limpiar fecha (string -> date)
  limpiarFecha(fechaStr: string): Date{
    if (fechaStr != null && fechaStr.trim() !== ''){
      try {
        const fechaLimpia: Date = new Date(fechaStr);
        return fechaLimpia;
      } catch (error) {
        console.error('Error al convertir la fecha' + error);
      }
    }
    return new Date();
  }

  // Comprobar formulario
  comprobarFormularioCrearEvento(): boolean{

    // Titulo
    if (this.evento.titulo == null || this.evento.titulo.trim() === '')
    {
      swal('Error', 'Introduce el titulo del evento', 'error');
      return false;
    }
    // Fechas
    if (this.evento.fechaInicio ==  null && this.evento.fechaFin ==  null)
    {
      swal('Error', 'Introduce las fechas del evento', 'error');
      return false;

    } else  if (this.evento.fechaInicio ==  null)
    {
      swal('Error', 'Introduce la fecha inicio del evento', 'error');
      return false;

    }else  if (this.evento.fechaFin ==  null)
    {
      swal('Error', 'Introduce la fecha fín del evento', 'error');
      return false;

    } else if ( this.evento.fechaFin.valueOf() < this.evento.fechaInicio.valueOf() )
    {
      console.log('Fin: ' + this.evento.fechaFin.valueOf() + ' Inicio: ' + this.evento.fechaInicio.valueOf() + ' Esto: ' + (this.evento.fechaFin.valueOf() < this.evento.fechaInicio.valueOf()));
      swal('Error', 'La fecha de inicio es posterior de la fecha fín', 'error');
      return false;
    }

    return true;
  }


}

