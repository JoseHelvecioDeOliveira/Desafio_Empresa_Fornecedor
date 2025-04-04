import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms'; // Importação do FormsModule
import { CommonModule } from '@angular/common';
import { CrudService } from '../../services/crud.service';
import { Item } from '../../models/item.model';

@Component({
  selector: 'app-crud-table',
  imports: [FormsModule, CommonModule],
  templateUrl: './crud-table.component.html',
  styleUrls: ['./crud-table.component.scss'],
})
export class CrudTableComponent implements OnInit {
  items: Item[] = [];
  newItem: Partial<Item> = {};

  constructor(private crudService: CrudService) {}

  ngOnInit(): void {
    this.items = this.crudService.getItems();
  }

  addItem(): void {
    if (this.newItem.name && this.newItem.description) {
      const newItem: Item = {
        id: this.items.length + 1,
        name: this.newItem.name,
        description: this.newItem.description,
      };
      this.crudService.addItem(newItem);
      this.items = this.crudService.getItems();
      this.newItem = {};
    }
  }

  editItem(item: Item): void {
    const updatedName = prompt('Edit Name:', item.name);
    const updatedDescription = prompt('Edit Description:', item.description);
    if (updatedName && updatedDescription) {
      const updatedItem: Item = { ...item, name: updatedName, description: updatedDescription };
      this.crudService.updateItem(updatedItem);
      this.items = this.crudService.getItems();
    }
  }

  deleteItem(id: number): void {
    this.crudService.deleteItem(id);
    this.items = this.crudService.getItems();
  }
}