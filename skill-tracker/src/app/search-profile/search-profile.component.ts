import { Component, OnInit, AfterViewInit, ViewChild  } from '@angular/core';
import {FormControl, Validators} from '@angular/forms';
import {MatPaginator, PageEvent} from '@angular/material/paginator';
import {MatSnackBar} from '@angular/material/snack-bar';
import {MatAccordion} from '@angular/material/expansion';
import { Skill, SkillTracker } from '../service/skill-tracker';
import { ProfileSearchService } from '../service/profile-search.service';

export interface SearchType {
  type: string;
  view: string;
}

const SKILL:Skill[] = [
  {skill:'HTML-CSS-JAVASCRIPT',level:10},
  {skill:'ANGULAR',level:10},
  {skill:'REACT',level:10},
  {skill:'SPRING',level:10},
  {skill:'RESTFUL',level:10},
  {skill:'HIBERNATE',level:10},
  {skill:'GIT',level:10},
  {skill:'DOCKER',level:10},
  {skill:'JENKINS',level:10},
  {skill:'AWS',level:10}
];

const NON_T_SKILL:Skill[] = [
  {skill:'SPOKEN',level:10},
  {skill:'COMMUNICATION',level:10},
  {skill:'APTITUDE',level:10}
];

const ELEMENT_DATA_1: SkillTracker[] = [
  {id: '', name: 'Varun Test Name display', associateId:'CTSA123456', mobile: '9865234170', email: 'VarunTestNamedisplay@test.test', skillLevals : SKILL , nonTechnicalSkillLevals : NON_T_SKILL, updateTime: new Date(), updatedBy: 'Test'},
  {id: '', name: 'Varun Test Name display', associateId:'CTSA123456', mobile: '9865234170', email: 'VarunTestNamedisplay@test.test', skillLevals : SKILL , nonTechnicalSkillLevals : NON_T_SKILL, updateTime: new Date(), updatedBy: 'Test'},
  {id: '', name: 'Varun Test Name display', associateId:'CTSA123456', mobile: '9865234170', email: 'VarunTestNamedisplay@test.test', skillLevals : SKILL , nonTechnicalSkillLevals : NON_T_SKILL, updateTime: new Date(), updatedBy: 'Test'},
  {id: '', name: 'Varun Test Name display', associateId:'CTSA123456', mobile: '9865234170', email: 'VarunTestNamedisplay@test.test', skillLevals : SKILL , nonTechnicalSkillLevals : NON_T_SKILL, updateTime: new Date(), updatedBy: 'Test'},
  {id: '', name: 'Varun Test Name display', associateId:'CTSA123456', mobile: '9865234170', email: 'VarunTestNamedisplay@test.test', skillLevals : SKILL , nonTechnicalSkillLevals : NON_T_SKILL, updateTime: new Date(), updatedBy: 'Test'},
  {id: '', name: 'Varun Test Name display', associateId:'CTSA123456', mobile: '9865234170', email: 'VarunTestNamedisplay@test.test', skillLevals : SKILL , nonTechnicalSkillLevals : NON_T_SKILL, updateTime: new Date(), updatedBy: 'Test'},
  {id: '', name: 'Varun Test Name display', associateId:'CTSA123456', mobile: '9865234170', email: 'VarunTestNamedisplay@test.test', skillLevals : SKILL , nonTechnicalSkillLevals : NON_T_SKILL, updateTime: new Date(), updatedBy: 'Test'},
  {id: '', name: 'Varun Test Name display', associateId:'CTSA123456', mobile: '9865234170', email: 'VarunTestNamedisplay@test.test', skillLevals : SKILL , nonTechnicalSkillLevals : NON_T_SKILL, updateTime: new Date(), updatedBy: 'Test'},
  {id: '', name: 'Varun Test Name display', associateId:'CTSA123456', mobile: '9865234170', email: 'VarunTestNamedisplay@test.test', skillLevals : SKILL , nonTechnicalSkillLevals : NON_T_SKILL, updateTime: new Date(), updatedBy: 'Test'},
  {id: '', name: 'Varun Test Name display', associateId:'CTSA123456', mobile: '9865234170', email: 'VarunTestNamedisplay@test.test', skillLevals : SKILL , nonTechnicalSkillLevals : NON_T_SKILL, updateTime: new Date(), updatedBy: 'Test'},
  {id: '', name: 'Varun Test Name display', associateId:'CTSA123456', mobile: '9865234170', email: 'VarunTestNamedisplay@test.test', skillLevals : SKILL , nonTechnicalSkillLevals : NON_T_SKILL, updateTime: new Date(), updatedBy: 'Test'},
  {id: '', name: 'Varun Test Name display', associateId:'CTSA123456', mobile: '9865234170', email: 'VarunTestNamedisplay@test.test', skillLevals : SKILL , nonTechnicalSkillLevals : NON_T_SKILL, updateTime: new Date(), updatedBy: 'Test'}
];

const ELEMENT_DATA: SkillTracker[] = [];

@Component({
  selector: 'app-search-profile',
  templateUrl: './search-profile.component.html',
  styleUrls: ['./search-profile.component.scss']
})
export class SearchProfileComponent implements OnInit, AfterViewInit {
 
  pageSizeOptions: number[] = [5];
  totalRows = 0;
  currentPage:number = 0;
  pageSize:number = 5;

  displayedColumns: string[] = ['skill', 'level'];
  isDataLoading:boolean = false;
  // dataSource = new MatTableDataSource<SkillTracker>(ELEMENT_DATA);

  // dataSource = ELEMENT_DATA;

  skillList:SkillTracker[] = ELEMENT_DATA;

  showAllert:boolean = false;

  searchTypes: SearchType[] = [
    {type: 'NAME', view: 'Name'},
    {type: 'ASSOCIATEID', view: 'Associate ID'},
    {type: 'SKILL', view: 'Skill'}
  ];

  searchValue = new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(30)]);
  selectedSearchType = new FormControl('', [Validators.required]);

  constructor(private _snackBar: MatSnackBar, private searchService:ProfileSearchService) {}

  // searchValue:string = '';
  // associateId:string = '';
  // skillSearchVal:string[] = [];
  
  // skillSearch = new FormControl('');
  // skillList: string[] = ['Extra cheese', 'Mushroom', 'Onion', 'Pepperoni', 'Sausage', 'Tomato'];

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  @ViewChild(MatAccordion) 
  accordion!: MatAccordion;

  public SearchProfileComponent(){
  }
  
  ngOnInit(): void {
    console.log("Start Search..");
    // this.totalRows = ELEMENT_DATA.length;
    //this.skillList = ELEMENT_DATA.slice(this.currentPage, this.currentPage + this.pageSize);
    this.loadData();
  }

  ngAfterViewInit(): void {
    //this.dataSource.paginator = this.paginator;
  }
  
  searchProfile(){
    this.showAllert = false;
    this.isDataLoading = true;
    if(this.selectedSearchType.valid && this.searchValue.valid){
      console.log("selectedSearchType >> ",this.selectedSearchType.value);
      console.log("searchValue >> ",this.searchValue.value);
      this.loadData();     
    }else{
      this._snackBar.open('All (*) Fields are mandatory', 'Undo', {
        duration: 3000
      });
    }
    // this.isDataLoading = false;
  }

  getSkills(skills:Skill[], index:number ){
    return skills.slice(index, index + this.pageSize);
  }

  getErrorMessage() {
    if (this.searchValue.hasError('required')) {
      return 'You must enter a value';
    }

    if (this.searchValue.hasError('maxlength')) {
      return 'Maximum 30 characters only allowed ';
    }

    if (this.selectedSearchType.hasError('required')) {
      return 'You must select a value';
    }

    return this.searchValue.hasError('minlength') ? 'Minimum 3 characters should be entered' : '';
  }

  getSelectErrorMessage() {
    return this.selectedSearchType.hasError('required') ? 'You must select a value' : '';
  }

  pageChanged(event: PageEvent){
    console.log({ event });
    this.pageSize = event.pageSize;
    this.currentPage = event.pageIndex;
    this.loadData();
  }

  loadData() {
    this.isDataLoading = true;
    
    console.log(this.skillList);
    this.isDataLoading = true;

    this.searchService.searchSkills(''+this.selectedSearchType.value,''+this.searchValue.value,this.currentPage).subscribe(
      response =>{
        console.log(JSON.parse(JSON.stringify(response)));
        let skillTrackers:SkillTracker[] = JSON.parse(JSON.stringify(response)).skillTrackers;
        this.skillList = skillTrackers;
        this.totalRows = JSON.parse(JSON.stringify(response)).pageable.totalElements;
        this.isDataLoading = false;
      }
    );
  }

}