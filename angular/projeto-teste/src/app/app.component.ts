import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'projeto-teste';
  ultimoId = 0;
  nome = '';
  adicionado = false;
  funcionarios = [];

  adicinar(){
    this.adicionado = true;
    console.log(`Adicionando ${this.nome}`);

    this.funcionarios.push({
      nome: this.nome,
      id: ++this.ultimoId
    });
  }

}
