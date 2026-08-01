package fraudetection.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}

/* Tirar este comment antes de lançar

    Comandos:
    save(entity)
    findById(Id) - Retorna o obj que encontrou
    findAll()
    deleteById(id)
    delete(entity)
    existsById(id)
    count() - conta quantos registos existem
    findAllById(ids)

 */