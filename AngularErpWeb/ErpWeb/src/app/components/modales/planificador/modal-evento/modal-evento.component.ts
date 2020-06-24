import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-modal-evento',
  templateUrl: './modal-evento.component.html',
  styleUrls: ['./modal-evento.component.css']
})
export class ModalEventoComponent implements OnInit {

  constructor() { }

  crearEventoPlanificador(): void {
    alert('Evento creado!');
  }

  ngOnInit(): void {
  }

}
