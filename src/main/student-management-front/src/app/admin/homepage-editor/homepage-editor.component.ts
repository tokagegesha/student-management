import {Component, OnInit, ViewChild, ViewContainerRef} from '@angular/core';
import {SubjectService} from "../subject/subject.service";
import {ToastsManager} from "ng2-toastr";
import {FileUploadService} from "../../shared/file-upload/file-upload.service";

@Component({
  selector: 'app-homepage-editor',
  templateUrl: './homepage-editor.component.html',
  styleUrls: ['./homepage-editor.component.css'],
  providers: [SubjectService, FileUploadService]
})
export class HomepageEditorComponent implements OnInit {
  @ViewChild('attachmentButton')
  attachmentButton: any;
  @ViewChild('clipboardArea')
  clipboardArea: any;

  private elementId;
  public initialContent = "test";
  public initialContentLoaded = false;
  public newAttachments: File[] = [];
  public attachments: File[] = null;
  public homepagehtmlModel;
  public saveEnabled=false;

  constructor(private subjectService: SubjectService,
              public toast: ToastsManager,
              private vRef: ViewContainerRef,
              private fileUploadService: FileUploadService) {
  }

  keyupHandlerFunction(html) {
    this.homepagehtmlModel = html;
    this.saveEnabled=true;
    console.log('keyUp', html);
  }

  editHomepage() {
    console.log("dsasda",this.homepagehtmlModel);
    this.subjectService.editSetting('homepage', this.homepagehtmlModel, this.toast);
  }

  ngOnInit() {
    this.toast.setRootViewContainerRef(this.vRef);

    this.subjectService.getSettingValue('homepage', this.toast).then(res => {
      this.initialContent = res.data[0].value;
      this.initialContentLoaded = true;
    });

    this.subjectService.getSettingValue('homepage-attachments', this.toast).then(res => {
      this.attachments = JSON.parse(res.data[0].value) || [];
    });
  }

  fileChange(event: any) {
    if (event.target.files.length > 0) {
      for (let i = 0; i < event.target.files.length; i++) {
        this.newAttachments.push(event.target.files[i]);
      }
    } else if (event.target.files.length == 0) {
      console.log('no files');
    }
  }

  uploadNewAttachments() {
    this.fileUploadService.uploadFiles(this.newAttachments, this.toast).then(result => {
      console.log('ატვირთვის პასუხი', result);
      if (result.data.length > 0) {
        for (let i = 0; i < result.data.length; i++)
          this.attachments.push(result.data[i])
        this.editAttachments();
        this.resetAttachments();
      }
    });
  }

  editAttachments() {
    this.subjectService.editSetting('homepage-attachments', JSON.stringify(this.attachments), this.toast).then(result => {
      console.log('dabrunda setting edit dan', result);
      this.attachments = JSON.parse(result.data[0].value);
    });
  }


  deleteAttachment(index) {
    if (confirm("ნამდვილად გსურთ მიმაგრებული ფაილის წაშლა?\n" + this.attachments[index].name)) {
      this.attachments.splice(index, 1);
      this.editAttachments();
    }
  }

  resetAttachments() {
    console.log(this.attachmentButton.nativeElement.files);
    this.attachmentButton.nativeElement.value = "";
    console.log(this.attachmentButton.nativeElement.files);
  }

  deleteNewAttachment(index) {
    this.newAttachments.splice(index, 1);
    if (this.newAttachments.length == 0) this.resetAttachments();
  }

  copyToClipboard(text) {
    console.log(text, this.clipboardArea.nativeElement);
    this.clipboardArea.nativeElement.value = text;
    this.clipboardArea.nativeElement.select();

    /* Copy the text inside the text field */
    document.execCommand("Copy");
  }
}
