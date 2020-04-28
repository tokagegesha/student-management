export class Pagination {
    public size: number;
    public page: number;
    public totalPages: number;
    public totalCount: number;
    public isLast: boolean;
    public isFirst: boolean;
    public sort: any;
    public pagesBefore: number[] = [];
    public pagesAfter: number[] = [];

    constructor(private defaultPageSize: number,
                private pageCountBefore: number,
                private pageCountAfter: number,
                public showJumpToPage: boolean = true,
                public showPageSize: boolean = true,
                public showRowNumbers: boolean = true,
                public previousLabel: string = 'წინა',
                public nextLabel: string = 'მომდევნო') {

    }

    setPaging(paging: any) {
        this.page = paging.pageNumber + 1;
        this.size = paging.pageSize || this.defaultPageSize;
        this.totalPages = paging.totalPages;
        this.totalCount = paging.totalCount;
        this.isLast = this.page >= this.totalPages;
        this.isFirst = this.page <= 1;
        this.sort = paging.sort;
        this.pagesBefore = [];
        this.pagesAfter = [];
        for (var i = this.page - this.pageCountBefore; i < this.page; i++) if (i > 0) this.pagesBefore.push(i)
        for (var i = this.page + 1; i <= this.page + this.pageCountAfter; i++) if (i <= this.totalPages) this.pagesAfter.push(i)
    }

    getPaging() {
        return {
            page: this.page - 1,
            size: this.size || this.defaultPageSize,
            sort: this.sort
        }
    }
}