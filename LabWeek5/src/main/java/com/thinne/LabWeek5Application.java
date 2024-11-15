package com.thinne;

import com.neovisionaries.i18n.CountryCode;
import com.thinne.backend.models.*;
import com.thinne.backend.repositories.*;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class LabWeek5Application {

    public static void main(String[] args) {
        SpringApplication.run(LabWeek5Application.class, args);
    }

    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private CandidateSkillRepository candidateSkillRepository;
    @Autowired
    private JobSkillRepository jobSkillRepository;

//    @Bean
//    CommandLineRunner initData() {
//        return args -> {
//            Faker faker = new Faker();
//            //dữ lệu mẫu cho candidate
//            List<Address> addresses = addressRepository.findAll();
//            for (int i = 0; i < 10; i++) {
//                Candidate candidate = new Candidate();
//                candidate.setDob(faker.date().birthday(18, 65).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//                candidate.setEmail(faker.internet().emailAddress());
//                candidate.setFullName(faker.name().fullName());
//                Address address = new Address();
//                address.setStreet(faker.address().streetName());
//                address.setCity(faker.address().city());
//                address.setCountry(faker.number().numberBetween(1, 200));
//                address.setNumber(faker.address().buildingNumber());
//                address.setZipcode(faker.address().zipCode());
//                addressRepository.save(address);
//                candidate.setAddress(address);
//                candidate.setPhone(faker.phoneNumber().phoneNumber().replaceAll("[^0-9]", "").substring(0, 10)); // Giới hạn độ dài số điện thoại                candidate.setAddress(addresses.get(faker.number().numberBetween(0, addresses.size())));
//                candidate.setPassword(faker.internet().password(8, 20)); // Đảm bảo mật khẩu đủ dài nhưng không vượt quá giới hạn
//                candidateRepository.save(candidate);
//            }
//
//            // Tạo dữ liệu mẫu cho bảng company
//            for (int i = 0; i < 10; i++) {
//                Company company = new Company();
//                company.setAbout(faker.company().catchPhrase());
//                company.setEmail(faker.internet().emailAddress());
//                company.setCompName(faker.company().name());
//                company.setPhone(faker.phoneNumber().phoneNumber().replaceAll("[^0-9]", "").substring(0, 10));// Giới hạn độ dài số điện thoại
//                company.setWebUrl(faker.internet().url());
//                Address address = new Address();
//                address.setStreet(faker.address().streetName());
//                address.setCity(faker.address().city());
//                address.setCountry(faker.number().numberBetween(1, 200));
//                address.setNumber(faker.address().buildingNumber());
//                address.setZipcode(faker.address().zipCode());
//                addressRepository.save(address);
//                company.setAddress(address);
//                company.setPassword(faker.internet().password(8, 20)); // Đảm bảo mật khẩu đủ dài nhưng không vượt quá giới hạn
//                companyRepository.save(company);
//            }
//            List<Company> companies = companyRepository.findAll();
//            // Tạo dữ liệu mẫu cho bảng job
//            for (int i = 0; i < 10; i++) {
//                Job job = new Job();
//                job.setJobDesc(faker.lorem().paragraph());
//                job.setJobName(faker.job().title());
//                job.setCompany(companies.get(faker.number().numberBetween(0, companies.size())));
//                jobRepository.save(job);
//            }
//            List<Job> jobs = jobRepository.findAll();
//            // Tạo dữ liệu mẫu cho bảng skill
//            for (int i = 0; i < 10; i++) {
//                Skill skill = new Skill();
//                skill.setSkillDescription(faker.lorem().sentence());
//                skill.setSkillName(faker.job().keySkills());
//                skill.setType(faker.options().option(SkillType.class));
//                skillRepository.save(skill);
//            }
//            List<Skill> skills = skillRepository.findAll();
//            // Tạo dữ liệu mẫu cho bảng candidate_skil
//            for (int i = 0; i < 10; i++) {
//                CandidateSkill candidateSkill = new CandidateSkill();
//                candidateSkill.setMoreInfos(faker.lorem().paragraph());
//                candidateSkill.setSkillLevel(faker.options().option(SkillLevel.class));
//
//                Candidate candidate = candidateRepository.findAll().get(faker.number().numberBetween(0, 10));
//                Skill skill = skills.get(faker.number().numberBetween(0, skills.size()));
//
//                CandidateSkillId candidateSkillId = new CandidateSkillId();
//                candidateSkillId.setCanId(candidate.getId());
//                candidateSkillId.setSkillId(skill.getId());
//
//                candidateSkill.setId(candidateSkillId);
//                candidateSkill.setCandidate(candidate);
//                candidateSkill.setSkill(skill);
//
//                candidateSkillRepository.save(candidateSkill);
//            }
//            // Tạo dữ liệu mẫu cho bảng job_skill
//            for (int i = 0; i < 10; i++) {
//                JobSkill jobSkill = new JobSkill();
//                jobSkill.setMoreInfos(faker.lorem().paragraph());
//                jobSkill.setSkillLevel(faker.options().option(SkillLevel.class));
//
//                Job job = jobs.get(faker.number().numberBetween(0, jobs.size()));
//                Skill skill = skills.get(faker.number().numberBetween(0, skills.size()));
//
//                JobSkillId jobSkillId = new JobSkillId();
//                jobSkillId.setJobId(job.getId());
//                jobSkillId.setSkillId(skill.getId());
//
//                jobSkill.setId(jobSkillId);
//                jobSkill.setJob(job);
//                jobSkill.setSkill(skill);
//
//                jobSkillRepository.save(jobSkill);
//            }
//        };
//    }

}
