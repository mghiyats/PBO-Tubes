import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.User;
import model.Group;
import model.Project;
import model.Task;
import model.TaskAssignment;
import tubes.pbo.A_Masuk;
import tubes.pbo.B_Daftar;
import tubes.pbo.C_Menu_KETUA;
import tubes.pbo.J_TugasSaya_ANGGOTA;
import tubes.pbo.I_Menu_ANGGOTA;
import tubes.pbo.G_ProgresTugas_KETUA;
import java.util.Date;

public class coverage {
    @Test
    public void testUserCoverage() {
        User user = new User("id", "uname", "n", "e", "p", "t");
        user.setId("idx");
        user.setUsername("uname2");
        user.setName("n2");
        user.setEmail("e2");
        user.setPassword("p2");
        user.setType("t2");
        assertEquals("idx", user.getId());
        assertEquals("uname2", user.getUsername());
        assertEquals("n2", user.getName());
        assertEquals("e2", user.getEmail());
        assertEquals("p2", user.getPassword());
        assertEquals("t2", user.getType());
    }

    @Test
    public void testGroupCoverage() {
        Group group = new Group("gid", "uid");
        group.setGroupId("gid2");
        group.setUserId("uid2");
        assertEquals("gid2", group.getGroupId());
        assertEquals("uid2", group.getUserId());
    }

    @Test
    public void testProjectCoverage() {
        Group group = new Group("gid", "uid");
        Project project = new Project("proj", group);
        project.setName("proj2");
        Group group2 = new Group("gid2", "uid2");
        project.setGroup(group2);
        assertEquals("proj2", project.getName());
        assertEquals(group2, project.getGroup());
    }

    @Test
    public void testTaskCoverage() {
        Date d = new Date();
        Task task = new Task("tid", "t", "desc", d, "open");
        task.setId("tid2");
        task.setTitle("t2");
        task.setDescription("desc2");
        Date d2 = new Date();
        task.setDueDate(d2);
        task.setStatus("closed");
        assertEquals("tid2", task.getId());
        assertEquals("t2", task.getTitle());
        assertEquals("desc2", task.getDescription());
        assertEquals(d2, task.getDueDate());
        assertEquals("closed", task.getStatus());
    }

    @Test
    public void testTaskAssignmentCoverage() {
        TaskAssignment ta = new TaskAssignment("uid", "tid", "job");
        ta.setUserId("uid2");
        ta.setTaskId("tid2");
        ta.setJobDesc("job2");
        assertEquals("uid2", ta.getUserId());
        assertEquals("tid2", ta.getTaskId());
        assertEquals("job2", ta.getJobDesc());
    }

    @Test
    public void testA_MasukConstructor() {
        A_Masuk masuk = new A_Masuk();
        assertNotNull(masuk);
    }

    @Test
    public void testB_DaftarConstructor() {
        B_Daftar daftar = new B_Daftar();
        assertNotNull(daftar);
    }

    @Test
    public void testC_Menu_KETUAConstructor() {
        C_Menu_KETUA menu = new C_Menu_KETUA("");
        assertNotNull(menu);
    }

    @Test
    public void testJ_TugasSaya_ANGGOTAConstructor() {
        J_TugasSaya_ANGGOTA tugas = new J_TugasSaya_ANGGOTA();
        assertNotNull(tugas);
    }

    @Test
    public void testI_Menu_ANGGOTAConstructor() {
        I_Menu_ANGGOTA menu = new I_Menu_ANGGOTA("");
        assertNotNull(menu);
    }

    @Test
    public void testG_ProgresTugas_KETUAConstructor() {
        G_ProgresTugas_KETUA progres = new G_ProgresTugas_KETUA();
        assertNotNull(progres);
    }
}