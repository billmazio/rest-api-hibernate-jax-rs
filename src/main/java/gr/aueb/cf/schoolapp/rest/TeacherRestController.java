package gr.aueb.cf.schoolapp.rest;

import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp.dto.TeacherReadOnlyDTO;
import gr.aueb.cf.schoolapp.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.validator.ValidatorUtil;
import jakarta.persistence.EntityNotFoundException;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Path("/teachers")
public class TeacherRestController {

    @Inject
    private ITeacherService teacherService;

    @Path("")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTeachersByLastname(@QueryParam("lastname") String lastname) throws gr.aueb.cf.schoolapp.service.exceptions.EntityNotFoundException {
        List<Teacher> teachers;
        try {
            teachers = teacherService.getTeachersByLastname(lastname);
            List<TeacherReadOnlyDTO> teachersDto = new ArrayList<>();
            for (Teacher teacher : teachers) {
                teachersDto.add(new TeacherReadOnlyDTO(teacher.getId(),
                        teacher.getFirstname(), teacher.getLastname()));
            }
            return Response.status(Response.Status.OK).entity(teachersDto).build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("NOT FOUND").build();
        }

    }

    @Path("/{teacherId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTeacher(@PathParam("teacherId") Long teacherId) throws gr.aueb.cf.schoolapp.service.exceptions.EntityNotFoundException {
        Teacher teacher;
        try {
            teacher = teacherService.getTeacherById(teacherId);
            TeacherUpdateDTO dto = new TeacherUpdateDTO(teacher.getId(),
                    teacher.getFirstname(), teacher.getLastname());
            return Response.status(Response.Status.OK).entity(dto).build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("NOT FOUND").build();
        }
    }

    @Path("")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTeacher(TeacherInsertDTO dto, @Context UriInfo uriInfo) {
        List<String> errors = ValidatorUtil.validateDTO(dto);
        if (!errors.isEmpty()) return Response.status(Response.Status.BAD_REQUEST).entity(errors).build();

        try {
            Teacher teacher = teacherService.insertTeacher(dto);
            TeacherReadOnlyDTO readOnlyDTO = map(teacher);
            UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
            return Response.created(uriBuilder.path(Long.toString(readOnlyDTO.getId())).build())
                    .entity(readOnlyDTO).build();
        } catch (Exception e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Teacher error in insert")
                    .build();
        }
    }


    @Path("/{teacherId}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTeacher(@PathParam("teacherId") Long teacherId, TeacherUpdateDTO dto) {
        List<String> errors = ValidatorUtil.validateDTO(dto);
        if (!errors.isEmpty()) return Response
                .status(Response.Status.BAD_REQUEST).entity(errors).build();

        try {
            if (!Objects.equals(dto.getId(), teacherId)) {
                return Response.status(Response.Status.UNAUTHORIZED).entity("Unauthorized").build();
            }
            dto.setId(teacherId);
            Teacher teacher = teacherService.updateTeacher(dto);
            TeacherReadOnlyDTO readOnlyDTO = map(teacher);
            return Response.status(Response.Status.OK).entity(readOnlyDTO).build();
        } catch (gr.aueb.cf.schoolapp.service.exceptions.EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Teacher Not Found").build();

        }
    }


    @Path("/{teacherId}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTeacher(@PathParam("teacherId")Long teacherId) {
        try {
            Teacher teacher = teacherService.getTeacherById(teacherId);
            teacherService.deleteTeacher(teacherId);
            TeacherReadOnlyDTO readOnlyDTO = map(teacher);
            return Response.status(Response.Status.OK).entity(readOnlyDTO).build();
        } catch (gr.aueb.cf.schoolapp.service.exceptions.EntityNotFoundException e1) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Teacher Not Found")
                    .build();
        }
    }
    private TeacherReadOnlyDTO map(Teacher teacher) {
        TeacherReadOnlyDTO readOnlyDTO = new TeacherReadOnlyDTO();
        readOnlyDTO.setId(teacher.getId());
        readOnlyDTO.setFirstname(teacher.getFirstname());
        readOnlyDTO.setLastname(teacher.getLastname());
        return readOnlyDTO;
    }


}
