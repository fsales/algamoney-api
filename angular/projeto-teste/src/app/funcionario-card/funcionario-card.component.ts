import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-funcionario-card',
  templateUrl: './funcionario-card.component.html',
  styleUrls: ['./funcionario-card.component.css']
})
export class FuncionarioCardComponent implements OnInit {

  @Input('obj') funcionario: any;

  constructor() { 
  }

  ngOnInit(): void {
  }

  estiloCartao(){

    return { 
        'border-width': `${this.funcionario.id} px` ,
        backgroundColor: this.funcionario.id % 2 === 0 ?'lightblue' : 'lightgreen'
    };
  }
}
