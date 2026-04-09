document.addEventListener("DOMContentLoaded", () => {



    /* ========================= HEADER SCROLL (ocultar header al bajar) ========================= */



    const header = document.querySelector(".header");

    window.addEventListener("scroll", () => {
        if (window.scrollY > 50) {
            header.classList.add("hide");
        } else {
            header.classList.remove("hide");
        }
    });



    /* ========================= TÍTULOS (animación al hacer scroll) ========================= */



    const titulos = document.querySelectorAll('.section-title, .section-title-destinos');

    const observerTitulos = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.classList.add('show');
            }
        });
    }, { threshold: 0.3 });

    titulos.forEach(titulo => observerTitulos.observe(titulo));



    /* ========================= Animacion cards seccion EXPERIENCIAS ========================= */



    const sectionExperiencias = document.querySelector('.experiencias');

    if (sectionExperiencias) {

        const observerExperiencias = new IntersectionObserver((entries) => {
            entries.forEach(entry => {
                if (entry.isIntersecting) {

                    const left = sectionExperiencias.querySelector('.card-left');
                    const center = sectionExperiencias.querySelector('.card-center');
                    const right = sectionExperiencias.querySelector('.card-right');

                    center.classList.add('show');

                    setTimeout(() => {
                        left.classList.add('show');
                        right.classList.add('show');
                    }, 400);

                    observerExperiencias.unobserve(entry.target);
                }
            });
        }, { threshold: 0.5 });

        observerExperiencias.observe(sectionExperiencias);
    }



    /* ========================= Animacion cards seccion DESTINOS ========================= */
    
    
    
    const destinoCards = document.querySelectorAll('.destino-card');

    const observerDestinos = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                setTimeout(() => {
                    entry.target.classList.add('show');
                }, 400);
            }
        });
    }, { threshold: 0.5 });

    destinoCards.forEach(card => observerDestinos.observe(card));



    /* ========================= Animacion cards seccion INSTRUCCIONES ========================= */



    const steps = document.querySelectorAll('.story-step');

    const observerSteps = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.classList.add('show');
                observerSteps.unobserve(entry.target);
            }
        });
    }, { threshold: 0.2 });

    steps.forEach(step => observerSteps.observe(step));


    /* ========================= Animacion cards y mapa seccion UBICACIÓN  ========================= */
    const ubicacionSection = document.querySelector('.ubicacion');

    if (ubicacionSection) {

        const ubicacionCards = ubicacionSection.querySelectorAll('.ubicacion-card');
        const mapa = ubicacionSection.querySelector('.mapa-pro');
        const subtitle = ubicacionSection.querySelector('.section-subtitle');

        const observerUbicacion = new IntersectionObserver(entries => {
            entries.forEach(entry => {
                if (entry.isIntersecting) {

                    subtitle?.classList.add('show');

                    ubicacionCards.forEach(card => {
                        card.classList.add('show');
                    });

                    setTimeout(() => {
                        mapa?.classList.add('show');
                    }, 500);

                }
            });
        }, { threshold: 0.3 });

        observerUbicacion.observe(ubicacionSection);
    }



    /* ========================= Animacion seccion CONTACTO ========================= */



    const contactoSection = document.querySelector('.contacto');

    if (contactoSection) {

        const form = contactoSection.querySelector('.formulario-pro');
        const subtitle = contactoSection.querySelector('.section-subtitle');
        const title = contactoSection.querySelector('.section-title');

        const observerForm = new IntersectionObserver(entries => {
            entries.forEach(entry => {
                if (entry.isIntersecting) {

                    title?.classList.add('show');
                    subtitle?.classList.add('show');

                    setTimeout(() => {
                        form?.classList.add('show');
                    }, 200);

                    observerForm.unobserve(entry.target);
                }
            });
        }, { threshold: 0.3 });

        observerForm.observe(contactoSection);
    }



    /* ========================= FOOTER ========================= */


    
    const footer = document.querySelector('.footer');

    if (footer) {

        const observerFooter = new IntersectionObserver(entries => {
            entries.forEach(entry => {
                if (entry.isIntersecting) {
                    footer.classList.add('show');
                    observerFooter.unobserve(entry.target);
                }
            });
        }, { threshold: 0.2 });

        observerFooter.observe(footer);
    }
});