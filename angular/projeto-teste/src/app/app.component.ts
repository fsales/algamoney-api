import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'projeto-teste';

  nome = 'teste';

  adicinar(nome: string){
    this.nome = nome;
    console.log(`Adicionando ${this.nome}`);
  }

  alterarNome(event: any){
    this.nome = event.target.value;
  }
}
