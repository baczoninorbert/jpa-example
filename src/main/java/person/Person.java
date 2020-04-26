package person;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    @Setter
    @Entity
    public class Person {

        public static enum  Gender {
            FEMALE, MALE;
        }

        @Id
        @GeneratedValue
        private Long id;

        @Column(nullable = false)
        private String name;

        @Column(nullable = false)
        private LocalDate dob;

        @Column(nullable = false)
        private Gender gender;

        @Embedded
        private Address address;
        private String email;
        private String profession;


    }
