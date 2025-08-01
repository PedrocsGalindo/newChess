import { Component } from '@angular/core';
import { BoardComponent } from './shared/board/board.component';
import { SidebarComponent } from './shared/sidebar/sidebar.component';
@Component({
  standalone:true,
  selector: 'app-root',
  imports: [BoardComponent,SidebarComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'chess';
}
