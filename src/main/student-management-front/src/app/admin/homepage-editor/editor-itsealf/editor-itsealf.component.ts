import {AfterViewInit, Component, EventEmitter, Input, OnDestroy, Output} from '@angular/core';

@Component({
  selector: 'simple-tiny',
  template: `<textarea id="{{elementId}}">{{homepagehtmlModel}}</textarea>`
})
export class EditorItsealfComponent implements AfterViewInit, OnDestroy {
  @Input() elementId: String;
  @Input() initialContent: String;

  @Output() onEditorKeyup = new EventEmitter<any>();

  editor;

  ngAfterViewInit() {
    tinymce.init({
      content: 'dgfhgjhjlkgjhkjkvvvvvvvvvvvvvvv',
      selector: '#' + this.elementId,
      plugins: ['link', 'image', 'lists', 'paste', 'paste', 'spellchecker', 'textcolor', 'textpattern', 'media', 'table'],
      skin_url: '/assets/skins/lightgray',
      setup: editor => {
        this.editor = editor;
        editor.on('click', () => {
          const content = editor.getContent();
          this.onEditorKeyup.emit(content);
        });
      },
      init_instance_callback: (editor: any) => {
        editor && this.initialContent && this.editor.setContent(this.initialContent)
      }
    });
  }

  ngOnDestroy() {
    tinymce.remove(this.editor);
  }
}
