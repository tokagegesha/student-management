import { StudentManagementFrontPage } from './app.po';

describe('student-management-front App', () => {
  let page: StudentManagementFrontPage;

  beforeEach(() => {
    page = new StudentManagementFrontPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!');
  });
});
