import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'capitalizar'
})
export class CapitalizarPipe implements PipeTransform {

  transform(palabra: string, todaLaFrase: boolean = false): string {


    palabra = palabra.toLocaleLowerCase();
    let nombres = palabra.split(' ');

    // Si queremos capitalizar todo
    if ( todaLaFrase ) {
      nombres = nombres.map( nombre => {
        return nombre[0].toUpperCase() + nombre.substr(1);
      });
    } else {
      nombres[0] = nombres[0][0].toUpperCase() + nombres[0].substr(1);
    }

    return nombres.join(' ');
  }

}
